
package com.nordicpeak.flowengine.integration.callback;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "IntegrationCallbackService", targetNamespace = "http://www.oeplatform.org/version/1.0/schemas/integration/callback", wsdlLocation = "file:/C:/Users/unlogic/Workspace/FlowEngineIntegrationCallback/docs/IntegrationCallbackService.wsdl")
public class IntegrationCallbackService extends Service
{

    private final static URL INTEGRATIONCALLBACKSERVICE_WSDL_LOCATION = IntegrationCallbackService.class.getResource("IntegrationCallbackService.wsdl");
    private final static QName INTEGRATIONCALLBACKSERVICE_QNAME = new QName("http://www.oeplatform.org/version/1.0/schemas/integration/callback", "IntegrationCallbackService");

    public IntegrationCallbackService() {
        super(__getWsdlLocation(), INTEGRATIONCALLBACKSERVICE_QNAME);
    }

    public IntegrationCallbackService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INTEGRATIONCALLBACKSERVICE_QNAME, features);
    }

    public IntegrationCallbackService(URL wsdlLocation) {
        super(wsdlLocation, INTEGRATIONCALLBACKSERVICE_QNAME);
    }

    public IntegrationCallbackService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INTEGRATIONCALLBACKSERVICE_QNAME, features);
    }

    public IntegrationCallbackService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IntegrationCallbackService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IntegrationCallback
     */
    @WebEndpoint(name = "IntegrationCallback")
    public IntegrationCallback getIntegrationCallback() {
        return super.getPort(new QName("http://www.oeplatform.org/version/1.0/schemas/integration/callback", "IntegrationCallback"), IntegrationCallback.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IntegrationCallback
     */
    @WebEndpoint(name = "IntegrationCallback")
    public IntegrationCallback getIntegrationCallback(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.oeplatform.org/version/1.0/schemas/integration/callback", "IntegrationCallback"), IntegrationCallback.class, features);
    }

    private static URL __getWsdlLocation() {
        return INTEGRATIONCALLBACKSERVICE_WSDL_LOCATION;
    }

}
