
package com.openexchange.admin.soap.reseller.group.reseller.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:09:53.131+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "NoSuchUserException", targetNamespace = "http://soap.reseller.admin.openexchange.com")
public class NoSuchUserException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchUserException noSuchUserException;

    public NoSuchUserException_Exception() {
        super();
    }

    public NoSuchUserException_Exception(String message) {
        super(message);
    }

    public NoSuchUserException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserException_Exception(String message, com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchUserException noSuchUserException) {
        super(message);
        this.noSuchUserException = noSuchUserException;
    }

    public NoSuchUserException_Exception(String message, com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchUserException noSuchUserException, Throwable cause) {
        super(message, cause);
        this.noSuchUserException = noSuchUserException;
    }

    public com.openexchange.admin.soap.reseller.group.reseller.soap.NoSuchUserException getFaultInfo() {
        return this.noSuchUserException;
    }
}
