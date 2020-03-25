package com.nordicpeak.flowengine.queries.textfieldquery.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import se.unlogic.hierarchy.core.annotations.InstanceManagerDependency;
import se.unlogic.hierarchy.core.annotations.ModuleSetting;
import se.unlogic.hierarchy.core.annotations.TextAreaSettingDescriptor;
import se.unlogic.hierarchy.core.annotations.TextFieldSettingDescriptor;
import se.unlogic.hierarchy.core.annotations.WebPublic;
import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.hierarchy.core.interfaces.ForegroundModuleResponse;
import se.unlogic.hierarchy.core.interfaces.SectionInterface;
import se.unlogic.hierarchy.core.interfaces.attributes.AttributeHandler;
import se.unlogic.hierarchy.core.interfaces.modules.descriptors.ForegroundModuleDescriptor;
import se.unlogic.hierarchy.core.utils.AttributeTagUtils;
import se.unlogic.hierarchy.core.utils.CRUDCallback;
import se.unlogic.hierarchy.foregroundmodules.AnnotatedForegroundModule;
import se.unlogic.hierarchy.foregroundmodules.userproviders.SimpleUser;
import se.unlogic.standardutils.annotations.SplitOnLineBreak;
import se.unlogic.standardutils.collections.CollectionUtils;
import se.unlogic.standardutils.dao.AdvancedAnnotatedDAOWrapper;
import se.unlogic.standardutils.dao.AnnotatedDAO;
import se.unlogic.standardutils.dao.AnnotatedDAOWrapper;
import se.unlogic.standardutils.dao.HighLevelQuery;
import se.unlogic.standardutils.dao.QueryParameterFactory;
import se.unlogic.standardutils.dao.SimpleAnnotatedDAOFactory;
import se.unlogic.standardutils.dao.querys.ArrayListQuery;
import se.unlogic.standardutils.datatypes.SimpleEntry;
import se.unlogic.standardutils.populators.IntegerPopulator;
import se.unlogic.standardutils.string.AnnotatedBeanTagSourceFactory;
import se.unlogic.standardutils.string.TagReplacer;
import se.unlogic.standardutils.xml.XMLParser;
import se.unlogic.standardutils.xml.XMLUtils;
import se.unlogic.webutils.http.HTTPResponse;
import se.unlogic.webutils.http.HTTPUtils;
import se.unlogic.webutils.http.RequestUtils;
import se.unlogic.webutils.http.SimpleRequest;
import se.unlogic.webutils.http.URIParser;

import com.nordicpeak.flowengine.beans.Flow;
import com.nordicpeak.flowengine.beans.QueryDescriptor;
import com.nordicpeak.flowengine.beans.Step;
import com.nordicpeak.flowengine.dao.FlowEngineDAOFactory;
import com.nordicpeak.flowengine.interfaces.MutableQueryDescriptor;
import com.nordicpeak.flowengine.interfaces.Query;
import com.nordicpeak.flowengine.interfaces.QueryHandler;
import com.nordicpeak.flowengine.queries.textfieldquery.TextFieldQuery;
import com.nordicpeak.flowengine.utils.UserAttributeTagUtils;

public class TextFieldQueryEndpointAdminModule extends AnnotatedForegroundModule implements CRUDCallback<User> {

	public static final AnnotatedBeanTagSourceFactory<User> USER_TAG_SOURCE_FACTORY = new AnnotatedBeanTagSourceFactory<User>(User.class, "$user.");

	@ModuleSetting
	@TextFieldSettingDescriptor(name = "Connection timeout", description = "The maximum time in seconds to wait for a connection")
	private Integer connectionTimeout = 10;

	@ModuleSetting
	@TextFieldSettingDescriptor(name = "Read timeout", description = "The maximum time in seconds to wait for a response")
	private Integer readTimeout = 10;

	@ModuleSetting
	@SplitOnLineBreak
	@TextAreaSettingDescriptor(name = "Allowed encodings", description = "Encodings allowed to use")
	private List<String> allowedEncodings = new ArrayList<String>(Arrays.asList("ISO-8859-1", "UTF-8"));

	@InstanceManagerDependency
	private QueryHandler queryHandler;

	private AnnotatedDAO<TextFieldQueryEndpoint> endpointDAO;
	private AnnotatedDAO<TextFieldQuery> queryDAO;
	private AnnotatedDAOWrapper<QueryDescriptor, Integer> queryDescriptorDAOWrapper;

	private QueryParameterFactory<TextFieldQuery, Integer> queryIDParamFactory;
	private QueryParameterFactory<TextFieldQueryEndpoint, Integer> endpointIDParamFactory;

	private TextFieldQueryEndpointCRUD endpointCRUD;

	@Override
	public void init(ForegroundModuleDescriptor moduleDescriptor, SectionInterface sectionInterface, DataSource dataSource) throws Exception {

		super.init(moduleDescriptor, sectionInterface, dataSource);

		if (!systemInterface.getInstanceHandler().addInstance(TextFieldQueryEndpointAdminModule.class, this)) {

			throw new RuntimeException("Unable to register module " + moduleDescriptor + " in global instance handler using key " + TextFieldQueryEndpointAdminModule.class.getSimpleName() + ", another instance is already registered using this key.");
		}
	}

	@Override
	protected void createDAOs(DataSource dataSource) throws Exception {

		SimpleAnnotatedDAOFactory daoFactory = new SimpleAnnotatedDAOFactory(dataSource);

		queryDAO = daoFactory.getDAO(TextFieldQuery.class);
		endpointDAO = daoFactory.getDAO(TextFieldQueryEndpoint.class);

		queryIDParamFactory = queryDAO.getParamFactory("queryID", Integer.class);
		endpointIDParamFactory = endpointDAO.getParamFactory("endpointID", Integer.class);

		AdvancedAnnotatedDAOWrapper<TextFieldQueryEndpoint, Integer> endpointDAOWrapper = endpointDAO.getAdvancedWrapper(Integer.class);
		endpointDAOWrapper.getGetAllQuery().addRelations(TextFieldQueryEndpoint.QUERIES_RELATION);
		endpointDAOWrapper.getGetAllQuery().disableAutoRelations(true);
		endpointDAOWrapper.getGetQuery().addRelations(TextFieldQueryEndpoint.QUERIES_RELATION, TextFieldQueryEndpoint.FIELDS_RELATION);
		endpointDAOWrapper.getGetQuery().disableAutoRelations(true);

		endpointCRUD = new TextFieldQueryEndpointCRUD(endpointDAOWrapper, this);

		FlowEngineDAOFactory flowDAOFactory = new FlowEngineDAOFactory(dataSource, systemInterface.getUserHandler(), systemInterface.getGroupHandler());

		queryDescriptorDAOWrapper = flowDAOFactory.getQueryDescriptorDAO().getWrapper(Integer.class);
		queryDescriptorDAOWrapper.addRelations(QueryDescriptor.STEP_RELATION, Step.FLOW_RELATION, Flow.FLOW_TYPE_RELATION, Flow.CATEGORY_RELATION);
		queryDescriptorDAOWrapper.setUseRelationsOnGet(true);
	}

	@Override
	public void unload() throws Exception {

		systemInterface.getInstanceHandler().removeInstance(TextFieldQueryEndpointAdminModule.class, this);

		super.unload();
	}

	@Override
	public ForegroundModuleResponse defaultMethod(HttpServletRequest req, HttpServletResponse res, User user, URIParser uriParser) throws Throwable {

		return endpointCRUD.list(req, res, user, uriParser, null);
	}

	@WebPublic
	public ForegroundModuleResponse show(HttpServletRequest req, HttpServletResponse res, User user, URIParser uriParser) throws Throwable {

		return endpointCRUD.show(req, res, user, uriParser);
	}

	@WebPublic
	public ForegroundModuleResponse add(HttpServletRequest req, HttpServletResponse res, User user, URIParser uriParser) throws Throwable {

		return endpointCRUD.add(req, res, user, uriParser);
	}

	@WebPublic
	public ForegroundModuleResponse update(HttpServletRequest req, HttpServletResponse res, User user, URIParser uriParser) throws Throwable {

		return endpointCRUD.update(req, res, user, uriParser);
	}

	@WebPublic
	public ForegroundModuleResponse delete(HttpServletRequest req, HttpServletResponse res, User user, URIParser uriParser) throws Throwable {

		return endpointCRUD.delete(req, res, user, uriParser);
	}

	public List<TextFieldQuery> getQueries(TextFieldQueryEndpoint endpoint) {

		try {
			ArrayListQuery<Integer> idQuery = new ArrayListQuery<Integer>(queryDAO.getDataSource(), "SELECT queryID FROM " + queryDAO.getTableName() + " WHERE endpointID = ?", IntegerPopulator.getPopulator());
			idQuery.setInt(1, endpoint.getEndpointID());

			List<Integer> queryIDs = idQuery.executeQuery();

			if (!CollectionUtils.isEmpty(queryIDs)) {

				List<TextFieldQuery> queries = new ArrayList<TextFieldQuery>(queryIDs.size());

				for (Integer queryID : queryIDs) {

					QueryDescriptor queryDescriptor = queryDescriptorDAOWrapper.get(queryID);

					if (queryDescriptor != null) {

						queries.add((TextFieldQuery) getQuery(queryDescriptor));
					}
				}

				return queries;
			}

		} catch (SQLException e) {

			log.error("Unable to get queries using endpoint " + endpoint, e);
		}

		return null;
	}

	public Query getQuery(MutableQueryDescriptor descriptor) throws SQLException {

		TextFieldQuery query = this.getQuery(descriptor.getQueryID());

		if (query == null) {

			return null;
		}

		query.init(descriptor, getFullAlias() + "/config/" + descriptor.getQueryID());

		return query;
	}

	public TextFieldQuery getQuery(Integer queryID) throws SQLException {

		HighLevelQuery<TextFieldQuery> getQuery = new HighLevelQuery<TextFieldQuery>();

		getQuery.addParameter(queryIDParamFactory.getParameter(queryID));

		TextFieldQuery query = queryDAO.get(getQuery);

		return query;
	}

	@Override
	public Document createDocument(HttpServletRequest req, URIParser uriParser, User user) {

		Document doc = XMLUtils.createDomDocument();

		Element documentElement = doc.createElement("Document");
		documentElement.appendChild(RequestUtils.getRequestInfoAsXML(doc, req, uriParser));
		documentElement.appendChild(this.moduleDescriptor.toXML(doc));

		doc.appendChild(documentElement);

		return doc;
	}

	public String getEndpointURL(TextFieldQueryEndpoint endpoint, User poster, AttributeHandler attributeHandler) {
		
		if (endpoint == null || CollectionUtils.isEmpty(endpoint.getFields())) {
			return null;
		}

		String address = endpoint.getAddress();

		User usedPoster = poster;
		
		if (usedPoster == null) {
			
			usedPoster = new SimpleUser();
		}
		
		TagReplacer tagReplacer = new TagReplacer();
		tagReplacer.addTagSource(USER_TAG_SOURCE_FACTORY.getTagSource(usedPoster));
		
		address = tagReplacer.replace(address);

		address = UserAttributeTagUtils.replaceTags(address, usedPoster, true);
		
		address = AttributeTagUtils.replaceTags(address, attributeHandler, false, true);
		
		return address;
	}
	
	public Map<String, String> getAPIFieldValues(String address, TextFieldQueryEndpoint endpoint, User poster, AttributeHandler attributeHandler) throws TextFieldAPIRequestException {
		
		if (address == null || endpoint == null || CollectionUtils.isEmpty(endpoint.getFields())) {
			return null;
		}

		log.info("User " + poster + " getting field values for api endpoint " + endpoint + " from: " + address);

		SimpleRequest simpleRequest = new SimpleRequest(address);

		simpleRequest.setFollowRedirects(false);
		simpleRequest.setConnectionTimeout(connectionTimeout * 1000);
		simpleRequest.setReadTimeout(readTimeout * 1000);

		if (endpoint.getUsername() != null && endpoint.getPassword() != null) {

			simpleRequest.setUsername(endpoint.getUsername());
			simpleRequest.setPassword(endpoint.getPassword());
		}

		List<Entry<String, String>> headerEntries = new ArrayList<Entry<String, String>>(2);

		headerEntries.add(new SimpleEntry<String, String>("NoLoginRedirect", "NoLoginRedirect"));
		simpleRequest.setHeaders(headerEntries);

		try {
			HTTPResponse response = HTTPUtils.sendHTTPGetRequest(simpleRequest, endpoint.getCharset());

			try {
				XMLParser parser = new XMLParser(XMLUtils.parseXML(response.getValue(), endpoint.getCharset().toString(), false, false));

				List<XMLParser> fields = parser.getNodes("/Fields/Field", true);

				Map<String, String> fieldValues = new HashMap<String, String>(endpoint.getFields().size());
				
				if (fields.size() > 0) {

					for (XMLParser field : fields) {
						
						String fieldName = field.getString("Name");
						
						if (endpoint.getFields().contains(fieldName)) {
							
							String fieldValue = field.getString("Value");
							
							fieldValues.put(fieldName, fieldValue);
						}
					}
				}
				
				return fieldValues;

			} catch (Exception e) {

				log.warn("Error parsing response XML for " + endpoint, e);
				throw new TextFieldAPIRequestException();
			}

		} catch (IOException e) {

			log.warn("Error connecting to " + endpoint, e);
			throw new TextFieldAPIRequestException();
		}
	}

	@Override
	public String getTitlePrefix() {

		return moduleDescriptor.getName();
	}

	public List<String> getAllowedEncodings() {

		return allowedEncodings;
	}

	public List<TextFieldQueryEndpoint> getEndpoints() throws SQLException {

		HighLevelQuery<TextFieldQueryEndpoint> query = new HighLevelQuery<TextFieldQueryEndpoint>();

		return endpointDAO.getAll(query);
	}

	public TextFieldQueryEndpoint getEndpoint(Integer endpointID) throws SQLException {

		HighLevelQuery<TextFieldQueryEndpoint> query = new HighLevelQuery<TextFieldQueryEndpoint>();
		query.addParameter(endpointIDParamFactory.getParameter(endpointID));

		return endpointDAO.get(query);
	}
	
}