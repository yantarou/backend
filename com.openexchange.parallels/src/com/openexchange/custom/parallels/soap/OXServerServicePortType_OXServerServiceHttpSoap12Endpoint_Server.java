
package com.openexchange.custom.parallels.soap;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2012-07-11T15:32:34.775+02:00
 * Generated source version: 2.6.1
 * 
 */
 
public class OXServerServicePortType_OXServerServiceHttpSoap12Endpoint_Server{

    protected OXServerServicePortType_OXServerServiceHttpSoap12Endpoint_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new OXServerServicePortTypeImpl();
        String address = "http://46.137.100.169/servlet/axis2/services/OXServerService.OXServerServiceHttpSoap12Endpoint/";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new OXServerServicePortType_OXServerServiceHttpSoap12Endpoint_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
