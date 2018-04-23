
package com.openexchange.admin.soap.group.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-01T18:50:57.189+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "DatabaseUpdateException", targetNamespace = "http://soap.admin.openexchange.com")
public class DatabaseUpdateException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.group.soap.DatabaseUpdateException databaseUpdateException;

    public DatabaseUpdateException_Exception() {
        super();
    }

    public DatabaseUpdateException_Exception(String message) {
        super(message);
    }

    public DatabaseUpdateException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseUpdateException_Exception(String message, com.openexchange.admin.soap.group.soap.DatabaseUpdateException databaseUpdateException) {
        super(message);
        this.databaseUpdateException = databaseUpdateException;
    }

    public DatabaseUpdateException_Exception(String message, com.openexchange.admin.soap.group.soap.DatabaseUpdateException databaseUpdateException, Throwable cause) {
        super(message, cause);
        this.databaseUpdateException = databaseUpdateException;
    }

    public com.openexchange.admin.soap.group.soap.DatabaseUpdateException getFaultInfo() {
        return this.databaseUpdateException;
    }
}
