package com.nordicpeak.flowengine.flowapprovalmodule.beans;

import java.lang.reflect.Field;
import java.util.List;

import se.unlogic.emailutils.populators.LowerCaseEmailPopulator;
import se.unlogic.hierarchy.core.beans.Group;
import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.standardutils.annotations.NoDuplicates;
import se.unlogic.standardutils.annotations.RequiredIfSet;
import se.unlogic.standardutils.annotations.SplitOnLineBreak;
import se.unlogic.standardutils.annotations.WebPopulate;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.ManyToOne;
import se.unlogic.standardutils.dao.annotations.OneToMany;
import se.unlogic.standardutils.dao.annotations.OrderBy;
import se.unlogic.standardutils.dao.annotations.SimplifiedRelation;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;

@Table(name = "flowapproval_activities")
@XMLElement(name = "Activity")
public class FlowApprovalActivity extends GeneratedElementable {

	public static final Field ACTIVITY_GROUP_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "activityGroup");
	public static final Field ACTIVITY_PROGRESSES_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "activityProgresses");
	public static final Field RESPONSIBLE_USERS_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "responsibleUsers");
	public static final Field RESPONSIBLE_GROUPS_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "responsibleGroups");
	public static final Field ASSIGNABLE_USERS_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "assignableUsers");
	public static final Field ASSIGNABLE_GROUPS_RELATION = ReflectionUtils.getField(FlowApprovalActivity.class, "assignableGroups");

	@Key
	@DAOManaged(autoGenerated = true)
	@XMLElement
	private Integer activityID;

	@DAOManaged(columnName = "activityGroupID")
	@ManyToOne
	@XMLElement
	private FlowApprovalActivityGroup activityGroup;

	@DAOManaged
	@OrderBy
	@WebPopulate(maxLength = 255, required = true)
	@XMLElement
	private String name;

	@DAOManaged
	@WebPopulate(maxLength = 255)
	@XMLElement
	private String shortDescription;

	@DAOManaged
	@WebPopulate(maxLength = 65535)
	@XMLElement
	private String description;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean showFlowInstance;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean pdfDownloadActivation;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean requireSigning;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean requireComment;

	@DAOManaged
	@OneToMany
	@XMLElement(fixCase = true)
	private List<FlowApprovalActivityResponsibleUser> responsibleUsers;

	@DAOManaged
	@OneToMany
	@SimplifiedRelation(table = "flowapproval_activity_groups", remoteValueColumnName = "groupID")
	@XMLElement(fixCase = true)
	private List<Group> responsibleGroups;

	@DAOManaged
	@NoDuplicates
	@SplitOnLineBreak
	@WebPopulate(maxLength = 255)
	@OneToMany(autoGet = true, autoAdd = true, autoUpdate = true)
	@SimplifiedRelation(table = "flowapproval_activity_resp_user_attribute", remoteValueColumnName = "attributeName")
	@XMLElement(fixCase = true)
	private List<String> responsibleUserAttributeNames;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean allowManagersToAssignOwner;

	@DAOManaged
	@OneToMany
	@SimplifiedRelation(table = "flowapproval_activity_assignable_users", remoteValueColumnName = "userID")
	@XMLElement(fixCase = true)
	private List<User> assignableUsers;

	@DAOManaged
	@OneToMany
	@SimplifiedRelation(table = "flowapproval_activity_assignable_groups", remoteValueColumnName = "groupID")
	@XMLElement(fixCase = true)
	private List<Group> assignableGroups;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean onlyUseGlobalNotifications;

	@DAOManaged
	@RequiredIfSet(paramNames = "onlyUseGlobalNotifications")
	@WebPopulate(maxLength = 255, populator = LowerCaseEmailPopulator.class)
	@XMLElement
	private String globalEmailAddress;

	@DAOManaged
	@WebPopulate(maxLength = 255)
	@XMLElement
	private String attributeName;

	@DAOManaged
	@WebPopulate
	@XMLElement
	private boolean invert;

	@DAOManaged
	@NoDuplicates
	@SplitOnLineBreak
	@RequiredIfSet(paramNames = "attributeName")
	@WebPopulate(maxLength = 1024)
	@OneToMany(autoGet = true, autoAdd = true, autoUpdate = true)
	@SimplifiedRelation(table = "flowapproval_activity_attribute_values", remoteValueColumnName = "value")
	@XMLElement(fixCase = true)
	private List<String> attributeValues;

	@DAOManaged
	@OneToMany
	@XMLElement(fixCase = true)
	private List<FlowApprovalActivityProgress> activityProgresses;

	public Integer getActivityID() {

		return activityID;
	}

	public void setActivityID(Integer activityID) {

		this.activityID = activityID;
	}

	public FlowApprovalActivityGroup getActivityGroup() {

		return activityGroup;
	}

	public void setActivityGroup(FlowApprovalActivityGroup activityGroup) {

		this.activityGroup = activityGroup;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getShortDescription() {

		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {

		this.shortDescription = shortDescription;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public List<FlowApprovalActivityResponsibleUser> getResponsibleUsers() {

		return responsibleUsers;
	}

	public void setResponsibleUsers(List<FlowApprovalActivityResponsibleUser> responsibleUsers) {

		this.responsibleUsers = responsibleUsers;
	}

	public List<Group> getResponsibleGroups() {

		return responsibleGroups;
	}

	public void setResponsibleGroups(List<Group> responsibleGroups) {

		this.responsibleGroups = responsibleGroups;
	}

	public List<FlowApprovalActivityProgress> getActivityProgresses() {

		return activityProgresses;
	}

	public void setActivityProgresses(List<FlowApprovalActivityProgress> activityProgresses) {

		this.activityProgresses = activityProgresses;
	}

	public String getAttributeName() {

		return attributeName;
	}

	public void setAttributeName(String attributeName) {

		this.attributeName = attributeName;
	}

	public boolean isInverted() {

		return invert;
	}

	public void setInverted(boolean invert) {

		this.invert = invert;
	}

	public List<String> getAttributeValues() {

		return attributeValues;
	}

	public void setAttributeValues(List<String> attributeValues) {

		this.attributeValues = attributeValues;
	}

	public boolean isRequireSigning() {

		return requireSigning;
	}

	public void setRequireSigning(boolean requireSigning) {

		this.requireSigning = requireSigning;
	}

	public boolean isRequireComment() {

		return requireComment;
	}

	public void setRequireComment(boolean requireComment) {

		this.requireComment = requireComment;
	}

	public String getGlobalEmailAddress() {

		return globalEmailAddress;
	}

	public void setGlobalEmailAddress(String globalEmailAddress) {

		this.globalEmailAddress = globalEmailAddress;
	}

	public boolean isOnlyUseGlobalNotifications() {

		return onlyUseGlobalNotifications;
	}

	public void setOnlyUseGlobalNotifications(boolean onlyUseGlobalNotifications) {

		this.onlyUseGlobalNotifications = onlyUseGlobalNotifications;
	}

	public List<String> getResponsibleUserAttributeNames() {

		return responsibleUserAttributeNames;
	}

	public void setResponsibleUserAttributeName(List<String> responsibleUserAttributeNames) {

		this.responsibleUserAttributeNames = responsibleUserAttributeNames;
	}

	public boolean isShowFlowInstance() {

		return showFlowInstance;
	}

	public void setShowFlowInstance(boolean showFlowInstance) {

		this.showFlowInstance = showFlowInstance;
	}

	public boolean isPDFDownloadActivation() {

		return pdfDownloadActivation;
	}

	public void setPDFDownloadActivation(boolean pdfDownloadActivation) {

		this.pdfDownloadActivation = pdfDownloadActivation;
	}

	public boolean isAllowManagersToAssignOwner() {

		return allowManagersToAssignOwner;
	}

	public void setAllowManagersToAssignOwner(boolean allowManagersToAssignOwner) {

		this.allowManagersToAssignOwner = allowManagersToAssignOwner;
	}

	public List<User> getAssignableUsers() {

		return assignableUsers;
	}

	public void setAssignableUsers(List<User> assignableUsers) {

		this.assignableUsers = assignableUsers;
	}

	public List<Group> getAssignableGroups() {

		return assignableGroups;
	}

	public void setAssignableGroups(List<Group> assignableGroups) {

		this.assignableGroups = assignableGroups;
	}

	@Override
	public String toString() {

		return StringUtils.toLogFormat(name, 30) + " (activityID: " + activityID + ")";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (activityID == null ? 0 : activityID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FlowApprovalActivity other = (FlowApprovalActivity) obj;
		if (activityID == null) {
			if (other.activityID != null) {
				return false;
			}
		} else if (!activityID.equals(other.activityID)) {
			return false;
		}
		return true;
	}

}
