package com.nordicpeak.flowengine.flowapprovalmodule.validationerrors;

import se.unlogic.standardutils.validation.ValidationError;
import se.unlogic.standardutils.xml.XMLElement;

@XMLElement(name = "validationError")
public class AssignableGroupNotFound extends ValidationError {
	
	private static final long serialVersionUID = -2301063033076132039L;
	@XMLElement
	private String groupName;
	
	public AssignableGroupNotFound(String groupName) {

		super("AssignableGroupNotFound");

		this.groupName = groupName;
	}
}
