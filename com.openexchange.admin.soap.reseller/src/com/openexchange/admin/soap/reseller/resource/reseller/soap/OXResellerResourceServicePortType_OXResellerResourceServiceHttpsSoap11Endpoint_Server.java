
package com.openexchange.admin.soap.reseller.resource.reseller.soap;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:10:25.013+02:00
 * Generated source version: 2.6.0
 *
 */

public class OXResellerResourceServicePortType_OXResellerResourceServiceHttpsSoap11Endpoint_Server{

    protected OXResellerResourceServicePortType_OXResellerResourceServiceHttpsSoap11Endpoint_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new OXResellerResourceServicePortTypeImpl();
        String address = "https://192.168.32.167/servlet/axis2/services/OXResellerResourceService.OXResellerResourceServiceHttpsSoap11Endpoint/";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new OXResellerResourceServicePortType_OXResellerResourceServiceHttpsSoap11Endpoint_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}