package com.nordicpeak.flowengine;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import se.unlogic.hierarchy.core.beans.User;

import com.nordicpeak.flowengine.exceptions.flow.FlowDefaultStatusNotFound;
import com.nordicpeak.flowengine.exceptions.flowinstancemanager.FlowInstanceManagerClosedException;
import com.nordicpeak.flowengine.exceptions.queryinstance.UnableToSaveQueryInstanceException;
import com.nordicpeak.flowengine.interfaces.InlinePaymentCallback;
import com.nordicpeak.flowengine.managers.MutableFlowInstanceManager;


public class BaseFlowModuleInlinePaymentCallback implements InlinePaymentCallback {

	private final BaseFlowModule baseFlowModule;
	private final String actionID;
	
	public BaseFlowModuleInlinePaymentCallback(BaseFlowModule baseFlowModule, String actionID) {

		super();
		this.baseFlowModule = baseFlowModule;
		this.actionID = actionID;
	}

	@Override
	public void paymentComplete(MutableFlowInstanceManager instanceManager, User user, HttpServletRequest req, String details, Map<String,String> eventAttributes) throws FlowInstanceManagerClosedException, UnableToSaveQueryInstanceException, FlowDefaultStatusNotFound, SQLException {

		baseFlowModule.inlinePaymentComplete(instanceManager, req, user, actionID, details, eventAttributes);
	}

	@Override
	public String getPaymentSuccessURL(MutableFlowInstanceManager instanceManager, HttpServletRequest req) {

		return baseFlowModule.getPaymentSuccessURL(instanceManager, req);
	}

	@Override
	public String getPaymentFailURL(MutableFlowInstanceManager instanceManager, HttpServletRequest req) {

		return baseFlowModule.getPaymentFailURL(instanceManager, req);
	}
	
	@Override
	public String getPaymentURL(MutableFlowInstanceManager instanceManager, HttpServletRequest req) {

		return baseFlowModule.getSaveAndSubmitURL(instanceManager, req);
	}

	@Override
	public String getActionID() {

		return actionID;
	}

}
