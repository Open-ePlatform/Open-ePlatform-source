package com.nordicpeak.flowengine.beans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import se.unlogic.standardutils.annotations.WebPopulate;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.OneToMany;
import se.unlogic.standardutils.dao.annotations.OrderBy;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;

@Table(name = "flowengine_standard_status_groups")
@XMLElement
public class StandardStatusGroup extends GeneratedElementable implements Serializable {

	private static final long serialVersionUID = -9064856969314028632L;
	
	public static final Field STANDARD_STATUSES_RELATION = ReflectionUtils.getField(StandardStatusGroup.class, "standardStatuses");

	@DAOManaged(autoGenerated = true)
	@Key
	@XMLElement
	private Integer statusGroupID;

	@DAOManaged
	@OrderBy
	@WebPopulate(required = true, maxLength = 255)
	@XMLElement
	private String name;

	@DAOManaged
	@OneToMany
	@XMLElement(fixCase = true)
	private List<StandardStatus> standardStatuses;

	public Integer getStatusGroupID() {

		return statusGroupID;
	}

	public void setStatusGroupID(Integer statusID) {

		this.statusGroupID = statusID;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public List<StandardStatus> getStandardStatuses() {
		return standardStatuses;
	}

	public void setStandardStatuses(List<StandardStatus> standardStatuses) {
		this.standardStatuses = standardStatuses;
	}

	@Override
	public String toString() {

		return StringUtils.toLogFormat(name, 30) + " (statusGroupID: " + statusGroupID + ")";
	}
}
