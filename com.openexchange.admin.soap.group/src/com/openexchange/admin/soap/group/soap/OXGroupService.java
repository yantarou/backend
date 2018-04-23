package com.openexchange.admin.soap.group.soap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-01T18:50:57.359+02:00
 * Generated source version: 2.6.0
 *
 */
@WebServiceClient(name = "OXGroupService",
                  // wsdlLocation = "null",
                  targetNamespace = "http://soap.admin.openexchange.com")
public class OXGroupService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.admin.openexchange.com", "OXGroupService");
    public final static QName OXGroupServiceHttpSoap11Endpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpSoap11Endpoint");
    public final static QName OXGroupServiceHttpEndpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpEndpoint");
    public final static QName OXGroupServiceHttpSoap12Endpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpSoap12Endpoint");
    public final static QName OXGroupServiceHttpsSoap12Endpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpsSoap12Endpoint");
    public final static QName OXGroupServiceHttpsEndpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpsEndpoint");
    public final static QName OXGroupServiceHttpsSoap11Endpoint = new QName("http://soap.admin.openexchange.com", "OXGroupServiceHttpsSoap11Endpoint");
    static {
        WSDL_LOCATION = null;
    }

    public OXGroupService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OXGroupService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OXGroupService() {
        super(WSDL_LOCATION, SERVICE);
    }


    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpSoap11Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpSoap11Endpoint() {
        return super.getPort(OXGroupServiceHttpSoap11Endpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpSoap11Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpSoap11Endpoint, OXGroupServicePortType.class, features);
    }
    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpEndpoint")
    public OXGroupServicePortType getOXGroupServiceHttpEndpoint() {
        return super.getPort(OXGroupServiceHttpEndpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpEndpoint")
    public OXGroupServicePortType getOXGroupServiceHttpEndpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpEndpoint, OXGroupServicePortType.class, features);
    }
    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpSoap12Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpSoap12Endpoint() {
        return super.getPort(OXGroupServiceHttpSoap12Endpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpSoap12Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpSoap12Endpoint, OXGroupServicePortType.class, features);
    }
    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsSoap12Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsSoap12Endpoint() {
        return super.getPort(OXGroupServiceHttpsSoap12Endpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsSoap12Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpsSoap12Endpoint, OXGroupServicePortType.class, features);
    }
    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsEndpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsEndpoint() {
        return super.getPort(OXGroupServiceHttpsEndpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsEndpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsEndpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpsEndpoint, OXGroupServicePortType.class, features);
    }
    /**
     *
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsSoap11Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsSoap11Endpoint() {
        return super.getPort(OXGroupServiceHttpsSoap11Endpoint, OXGroupServicePortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OXGroupServicePortType
     */
    @WebEndpoint(name = "OXGroupServiceHttpsSoap11Endpoint")
    public OXGroupServicePortType getOXGroupServiceHttpsSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(OXGroupServiceHttpsSoap11Endpoint, OXGroupServicePortType.class, features);
    }

}