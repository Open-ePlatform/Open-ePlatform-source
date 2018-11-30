package com.nordicpeak.flowengine.beans;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import se.unlogic.hierarchy.core.validationerrors.UnableToParseFileValidationError;
import se.unlogic.standardutils.annotations.WebPopulate;
import se.unlogic.standardutils.base64.Base64;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.ManyToOne;
import se.unlogic.standardutils.dao.annotations.OrderBy;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.populators.StringPopulator;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.validation.ValidationError;
import se.unlogic.standardutils.validation.ValidationException;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;
import se.unlogic.standardutils.xml.XMLParser;
import se.unlogic.standardutils.xml.XMLParserPopulateable;
import se.unlogic.standardutils.xml.XMLValidationUtils;
import se.unlogic.webutils.populators.StringHTTPURLPopulator;

@Table(name = "flowengine_flow_forms")
@XMLElement
public class FlowForm extends GeneratedElementable implements Serializable, XMLParserPopulateable {
	
	
	private static final long serialVersionUID = -2717780511646598263L;
	
	public static final Field FLOW_RELATION = ReflectionUtils.getField(FlowForm.class, "flow");
	
	@DAOManaged(autoGenerated = true)
	@Key
	@XMLElement
	private Integer flowFormID;
	
	@DAOManaged
	@OrderBy
	@WebPopulate(maxLength = 255)
	@XMLElement
	private String name;
	
	@DAOManaged(columnName = "flowID")
	@ManyToOne
	private Flow flow;
	
	@DAOManaged
	@WebPopulate(maxLength = 1024, populator = StringHTTPURLPopulator.class)
	@XMLElement
	private String externalURL;
	
	@DAOManaged
	@XMLElement
	private String fileExtension;
	
	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean showExternalLinkIcon;
	
	private transient byte[] importFileContents = null;
	
	public Integer getFlowFormID() {
		
		return flowFormID;
	}

	public void setFlowFormID(Integer flowFormID) {

		this.flowFormID = flowFormID;
	}

	public String getRawName() {

		return name;
	}

	public String getName() {

		if (name != null) {

			return name;
		}

		if (flow != null) {

			return flow.getName();
		}

		return null;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Flow getFlow() {

		return flow;
	}

	public void setFlow(Flow flow) {

		this.flow = flow;
	}

	public String getExternalURL() {

		return externalURL;
	}

	public void setExternalURL(String externalPDF) {

		this.externalURL = externalPDF;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	@Override
	public String toString() {

		return "name: " + name + " (ID: " + flowFormID + ")";
	}

	public byte[] getImportFileContents() {
		return importFileContents;
	}

	@Override
	public void populate(XMLParser xmlParser) throws ValidationException {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		this.name = XMLValidationUtils.validateParameter("name", xmlParser, false, 1, 255, StringPopulator.getPopulator(), errors);
		this.fileExtension = XMLValidationUtils.validateParameter("fileExtension", xmlParser, false, 1, 4, StringPopulator.getPopulator(), errors);
		this.externalURL = XMLValidationUtils.validateParameter("externalURL", xmlParser, false, 1, 1024, StringPopulator.getPopulator(), errors);
		
		if(externalURL == null && fileExtension == null) {
			
			this.fileExtension = "pdf";
		}
		
		if (externalURL == null) {
			
			try {
				importFileContents = Base64.decode(xmlParser.getString("file"));
				
			} catch (IOException e) {
				
				errors.add(new UnableToParseFileValidationError(name));
			}
		}
		
		this.showExternalLinkIcon = xmlParser.getPrimitiveBoolean("showExternalLinkIcon");
		
		if (!errors.isEmpty()) {
			
			throw new ValidationException(errors);
		}
		
	}
}
