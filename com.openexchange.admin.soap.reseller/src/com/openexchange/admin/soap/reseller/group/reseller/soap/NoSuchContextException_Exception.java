
package com.openexchange.admin.soap.reseller.group.reseller.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:09:53.151+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "NoSuchContextException", targetNamespace = "http://soap.reseller.admin.openexchange.com")
public class NoSuchContextException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchContextException noSuchContextException;

    public NoSuchContextException_Exception() {
        super();
    }

    public NoSuchContextException_Exception(String message) {
        super(message);
    }

    public NoSuchContextException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchContextException_Exception(String message, com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchContextException noSuchContextException) {
        super(message);
        this.noSuchContextException = noSuchContextException;
    }

    public NoSuchContextException_Exception(String message, com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchContextException noSuchContextException, Throwable cause) {
        super(message, cause);
        this.noSuchContextException = noSuchContextException;
    }

    public com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchContextException getFaultInfo() {
        return this.noSuchContextException;
    }
}
