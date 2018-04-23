package com.openexchange.push.soap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.12
 * 2015-09-02T13:11:52.579+02:00
 * Generated source version: 2.7.12
 * 
 */
@WebServiceClient(name = "PushSoapInterface", 
                  wsdlLocation = "null",
                  targetNamespace = "http://soap.push.openexchange.com") 
public class PushSoapInterface extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.push.openexchange.com", "PushSoapInterface");
    public final static QName PushSoapInterfaceHttpSoap12Endpoint = new QName("http://soap.push.openexchange.com", "PushSoapInterfaceHttpSoap12Endpoint");
    public final static QName PushSoapInterfaceHttpSoap11Endpoint = new QName("http://soap.push.openexchange.com", "PushSoapInterfaceHttpSoap11Endpoint");
    public final static QName PushSoapInterfaceHttpEndpoint = new QName("http://soap.push.openexchange.com", "PushSoapInterfaceHttpEndpoint");
    static {
        WSDL_LOCATION = null;
    }

    public PushSoapInterface(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PushSoapInterface(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PushSoapInterface() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpSoap12Endpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpSoap12Endpoint() {
        return super.getPort(PushSoapInterfaceHttpSoap12Endpoint, PushSoapInterfacePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpSoap12Endpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(PushSoapInterfaceHttpSoap12Endpoint, PushSoapInterfacePortType.class, features);
    }
    /**
     *
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpSoap11Endpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpSoap11Endpoint() {
        return super.getPort(PushSoapInterfaceHttpSoap11Endpoint, PushSoapInterfacePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpSoap11Endpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(PushSoapInterfaceHttpSoap11Endpoint, PushSoapInterfacePortType.class, features);
    }
    /**
     *
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpEndpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpEndpoint() {
        return super.getPort(PushSoapInterfaceHttpEndpoint, PushSoapInterfacePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PushSoapInterfacePortType
     */
    @WebEndpoint(name = "PushSoapInterfaceHttpEndpoint")
    public PushSoapInterfacePortType getPushSoapInterfaceHttpEndpoint(WebServiceFeature... features) {
        return super.getPort(PushSoapInterfaceHttpEndpoint, PushSoapInterfacePortType.class, features);
    }

}
