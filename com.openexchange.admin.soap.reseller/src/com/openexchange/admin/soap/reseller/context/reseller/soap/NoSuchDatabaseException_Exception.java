
package com.openexchange.admin.soap.reseller.context.reseller.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:28:46.065+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "NoSuchDatabaseException", targetNamespace = "http://soap.reseller.admin.openexchange.com")
public class NoSuchDatabaseException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.reseller.context.reseller.soap.NoSuchDatabaseException noSuchDatabaseException;

    public NoSuchDatabaseException_Exception() {
        super();
    }

    public NoSuchDatabaseException_Exception(String message) {
        super(message);
    }

    public NoSuchDatabaseException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDatabaseException_Exception(String message, com.openexchange.admin.soap.reseller.context.reseller.soap.NoSuchDatabaseException noSuchDatabaseException) {
        super(message);
        this.noSuchDatabaseException = noSuchDatabaseException;
    }

    public NoSuchDatabaseException_Exception(String message, com.openexchange.admin.soap.reseller.context.reseller.soap.NoSuchDatabaseException noSuchDatabaseException, Throwable cause) {
        super(message, cause);
        this.noSuchDatabaseException = noSuchDatabaseException;
    }

    public com.openexchange.admin.soap.reseller.context.reseller.soap.NoSuchDatabaseException getFaultInfo() {
        return this.noSuchDatabaseException;
    }
}
