package com.nordicpeak.flowengine.beans;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import se.unlogic.hierarchy.core.beans.Group;
import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.hierarchy.core.handlers.SourceAttributeHandler;
import se.unlogic.hierarchy.core.interfaces.attributes.AttributeHandler;
import se.unlogic.hierarchy.core.interfaces.attributes.AttributeSource;
import se.unlogic.hierarchy.core.interfaces.attributes.MutableAttributeHandler;
import se.unlogic.standardutils.annotations.NoDuplicates;
import se.unlogic.standardutils.annotations.WebPopulate;
import se.unlogic.standardutils.collections.CollectionUtils;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.ManyToOne;
import se.unlogic.standardutils.dao.annotations.OneToMany;
import se.unlogic.standardutils.dao.annotations.OrderBy;
import se.unlogic.standardutils.dao.annotations.SimplifiedRelation;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.date.DateTimeStringyfier;
import se.unlogic.standardutils.enums.Order;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.string.StringTag;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;
import se.unlogic.standardutils.xml.XMLGeneratorDocument;
import se.unlogic.standardutils.xml.XMLUtils;

import com.nordicpeak.flowengine.enums.Priority;
import com.nordicpeak.flowengine.interfaces.ImmutableFlowInstance;

@Table(name = "flowengine_flow_instances")
@XMLElement
public class FlowInstance extends GeneratedElementable implements ImmutableFlowInstance, AttributeSource {

	private static final long serialVersionUID = -4068908917476759312L;

	public static final Field FLOW_RELATION = ReflectionUtils.getField(FlowInstance.class, "flow");
	public static final Field STATUS_RELATION = ReflectionUtils.getField(FlowInstance.class, "status");
	public static final Field INTERNAL_MESSAGES_RELATION = ReflectionUtils.getField(FlowInstance.class, "internalMessages");
	public static final Field EXTERNAL_MESSAGES_RELATION = ReflectionUtils.getField(FlowInstance.class, "externalMessages");
	public static final Field EVENTS_RELATION = ReflectionUtils.getField(FlowInstance.class, "events");
	public static final Field OWNERS_RELATION = ReflectionUtils.getField(FlowInstance.class, "owners");
	public static final Field MANAGERS_RELATION = ReflectionUtils.getField(FlowInstance.class, "managers");
	public static final Field ATTRIBUTES_RELATION = ReflectionUtils.getField(FlowInstance.class,"attributes");
	
	public static final Field POSTER_FIELD = ReflectionUtils.getField(FlowInstance.class,"poster");
	public static final Field EDITOR_FIELD = ReflectionUtils.getField(FlowInstance.class,"editor");
	
	public static final Field SKIP_USER_ATTRIBUTES = ReflectionUtils.getField(FlowInstance.class,"SKIP_USER_ATTRIBUTES");

	@StringTag
	@DAOManaged(autoGenerated = true)
	@Key
	@XMLElement
	private Integer flowInstanceID;
	
	@DAOManaged(dontUpdateIfNull = true)
	private User poster;

	@DAOManaged(dontUpdateIfNull = true)
	@OneToMany
	@SimplifiedRelation(table = "flowengine_flow_instance_owners", remoteValueColumnName = "userID")
	@NoDuplicates
	@XMLElement
	private List<User> owners;

	@DAOManaged
	@OrderBy(order = Order.DESC)
	@XMLElement
	private Timestamp added;

	@DAOManaged(dontUpdateIfNull = true)
	private User editor;

	@DAOManaged
	@XMLElement
	private Timestamp updated;

	@StringTag(valueFormatter=DateTimeStringyfier.class)
	@DAOManaged
	@XMLElement
	private Timestamp firstSubmitted;
	
	@DAOManaged
	@XMLElement
	private boolean fullyPopulated;

	@DAOManaged(columnName = "flowID")
	@ManyToOne
	@XMLElement
	private Flow flow;

	@DAOManaged
	@XMLElement
	private Integer stepID;

	@DAOManaged(columnName = "statusID")
	@ManyToOne
	@XMLElement
	private Status status;

	@DAOManaged
	@XMLElement
	private Timestamp lastStatusChange;

	@DAOManaged
	@OneToMany
	@XMLElement
	private List<InternalMessage> internalMessages;

	@DAOManaged
	@OneToMany
	@XMLElement
	private List<ExternalMessage> externalMessages;

	@DAOManaged
	@OneToMany
	@SimplifiedRelation(table = "flowengine_flow_instance_managers", remoteValueColumnName = "userID")
	@WebPopulate
	@NoDuplicates
	@XMLElement
	private List<User> managers;
	
	@DAOManaged
	@OneToMany
	@SimplifiedRelation(table = "flowengine_flow_instance_manager_groups", remoteValueColumnName = "groupID")
	@WebPopulate
	@NoDuplicates
	@XMLElement
	private List<Group> managerGroups;

	@DAOManaged
	@OneToMany
	@XMLElement
	private List<FlowInstanceEvent> events;

	@DAOManaged
	@OneToMany
	@XMLElement
	private List<UserBookmark> bookmarks;

	@XMLElement
	private Priority priority;

	@DAOManaged
	@XMLElement
	private Integer profileID;

	@DAOManaged
	@OneToMany
	@XMLElement(fixCase=true)
	private List<FlowInstanceAttribute> attributes;
	
	@XMLElement
	private boolean remote;

	private SourceAttributeHandler attributeHandler;

	@Override
	public List<FlowInstanceAttribute> getAttributes() {

		return attributes;
	}

	public void setAttributes(List<FlowInstanceAttribute> attributes) {

		this.attributes = attributes;
	}

	@Override
	public void addAttribute(String name, String value) {

		if(this.attributes == null){

			attributes = new ArrayList<FlowInstanceAttribute>();
		}

		attributes.add(new FlowInstanceAttribute(name, value));
	}

	@Override
	public synchronized MutableAttributeHandler getAttributeHandler() {

		if(attributeHandler == null){

			this.attributeHandler = new SourceAttributeHandler(this, 255, 65536);
		}

		return attributeHandler;
	}

	@Override
	public Integer getFlowInstanceID() {

		return flowInstanceID;
	}

	public void setFlowInstanceID(Integer flowInstanceID) {

		this.flowInstanceID = flowInstanceID;
	}
	
	@Override
	public User getPoster() {

		return poster;
	}

	public void setPoster(User poster) {

		this.poster = poster;
	}

	@Override
	public List<User> getOwners() {

		return owners;
	}

	public void setOwners(List<User> owners) {

		this.owners = owners;
	}

	@Override
	public Timestamp getAdded() {

		return added;
	}

	public void setAdded(Timestamp added) {

		this.added = added;
	}

	@Override
	public User getEditor() {

		return editor;
	}

	public void setEditor(User editor) {

		this.editor = editor;
	}

	@Override
	public Timestamp getUpdated() {

		return updated;
	}

	public void setUpdated(Timestamp updated) {

		this.updated = updated;
	}

	@Override
	public Integer getStepID() {

		return stepID;
	}

	public void setStepID(Integer stepID) {

		this.stepID = stepID;
	}

	@Override
	public Flow getFlow() {

		return flow;
	}

	public void setFlow(Flow flow) {

		this.flow = flow;
	}

	@Override
	public Status getStatus() {

		return status;
	}

	public void setStatus(Status status) {

		this.status = status;
	}

	@Override
	public boolean isFullyPopulated() {

		return fullyPopulated;
	}

	public void setFullyPopulated(boolean fullyPopulated) {

		this.fullyPopulated = fullyPopulated;
	}

	@Override
	public String toString() {

		if (flow != null) {

			return StringUtils.toLogFormat(flow.getName(), 30) + " (flowInstanceID: " + flowInstanceID + ", flowID: " + flow.getFlowID() + ")";
		}

		return "FlowInstance (ID:" + flowInstanceID + ")";
	}

	@Override
	public Timestamp getLastStatusChange() {

		return lastStatusChange;
	}

	public void setLastStatusChange(Timestamp lastStatusChange) {

		this.lastStatusChange = lastStatusChange;
	}

	@Override
	public List<InternalMessage> getInternalMessages() {

		return internalMessages;
	}

	public void setInternalMessages(List<InternalMessage> internalMessages) {

		this.internalMessages = internalMessages;
	}

	@Override
	public List<ExternalMessage> getExternalMessages() {

		return externalMessages;
	}

	public void setExternalMessages(List<ExternalMessage> externalMessages) {

		this.externalMessages = externalMessages;
	}

	@Override
	public List<User> getManagers() {

		return managers;
	}

	public void setManagers(List<User> managers) {

		this.managers = managers;
	}
	
	public List<Group> getManagerGroups() {
		return managerGroups;
	}
	
	public void setManagerGroups(List<Group> managerGroups) {
		this.managerGroups = managerGroups;
	}
	
	@Override
	public List<FlowInstanceEvent> getEvents() {

		return events;
	}

	public void setEvents(List<FlowInstanceEvent> events) {

		this.events = events;
	}

	public Priority getPriority() {

		return priority;
	}

	public void setPriority(Priority priority) {

		this.priority = priority;
	}

	@Override
	public Integer getProfileID() {

		return profileID;
	}

	public void setProfileID(Integer profileID) {

		this.profileID = profileID;
	}

	public List<UserBookmark> getBookmarks() {

		return bookmarks;
	}

	public void setBookmarks(List<UserBookmark> bookmarks) {

		this.bookmarks = bookmarks;
	}

	@Override
	public Timestamp getFirstSubmitted() {
		
		return firstSubmitted;
	}

	
	public void setFirstSubmitted(Timestamp firstSubmitted) {
	
		this.firstSubmitted = firstSubmitted;
	}
	
	@Override
	public Element toXML(Document doc) {
		
		Element flowInstanceElement = super.toXML(doc);

		boolean skipUserAttributes = doc instanceof XMLGeneratorDocument && ((XMLGeneratorDocument)doc).isIgnoredField(SKIP_USER_ATTRIBUTES);
		
		if(poster != null){

			Element userElement = poster.toXML(doc);
			
			if(!skipUserAttributes){
				
				AttributeHandler attributeHandler = poster.getAttributeHandler();
				
				if(attributeHandler != null && !attributeHandler.isEmpty()){
					
					userElement.appendChild(attributeHandler.toXML(doc));
				}
			}

			Element posterElement = XMLUtils.appendNewElement(doc, flowInstanceElement, "poster");

			posterElement.appendChild(userElement);
		}
		
		if (editor != null) {
			
			Element userElement = editor.toXML(doc);
			
			if(!skipUserAttributes){
				
				AttributeHandler attributeHandler = editor.getAttributeHandler();
				
				if (attributeHandler != null && !attributeHandler.isEmpty()) {
					
					userElement.appendChild(attributeHandler.toXML(doc));
				}
			}
			
			Element editorElement = XMLUtils.appendNewElement(doc, flowInstanceElement, "editor");
			
			editorElement.appendChild(userElement);
		}
		
		if (!CollectionUtils.isEmpty(owners)) {
			
			Element ownersElement = XMLUtils.appendNewElement(doc, flowInstanceElement, "Owners");
			
			for (User owner : owners) {
				
				Element userElement = owner.toXML(doc);
				
				if(!skipUserAttributes){
					
					AttributeHandler attributeHandler = owner.getAttributeHandler();
					
					if (attributeHandler != null && !attributeHandler.isEmpty()) {
						
						userElement.appendChild(attributeHandler.toXML(doc));
					}
				}
				
				ownersElement.appendChild(userElement);
			}
		}
		
		return flowInstanceElement;
	}

	
	public boolean isRemote() {
	
		return remote;
	}

	
	public void setRemote(boolean remote) {
	
		this.remote = remote;
	}
}
