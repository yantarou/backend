
package com.openexchange.admin.soap.user.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-01T18:24:58.884+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "NoSuchFilestoreException", targetNamespace = "http://soap.admin.openexchange.com")
public class NoSuchFilestoreException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.user.soap.NoSuchFilestoreException noSuchFilestoreException;

    public NoSuchFilestoreException_Exception() {
        super();
    }

    public NoSuchFilestoreException_Exception(String message) {
        super(message);
    }

    public NoSuchFilestoreException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchFilestoreException_Exception(String message, com.openexchange.admin.soap.user.soap.NoSuchFilestoreException noSuchFilestoreException) {
        super(message);
        this.noSuchFilestoreException = noSuchFilestoreException;
    }

    public NoSuchFilestoreException_Exception(String message, com.openexchange.admin.soap.user.soap.NoSuchFilestoreException noSuchFilestoreException, Throwable cause) {
        super(message, cause);
        this.noSuchFilestoreException = noSuchFilestoreException;
    }

    public com.openexchange.admin.soap.user.soap.NoSuchFilestoreException getFaultInfo() {
        return this.noSuchFilestoreException;
    }
}
