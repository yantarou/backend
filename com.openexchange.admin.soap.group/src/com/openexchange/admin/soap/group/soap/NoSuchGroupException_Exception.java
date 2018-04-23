
package com.openexchange.admin.soap.group.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-01T18:50:57.164+02:00
 * Generated source version: 2.6.0
 */

@WebFault(name = "NoSuchGroupException", targetNamespace = "http://soap.admin.openexchange.com")
public class NoSuchGroupException_Exception extends java.lang.Exception {

    private com.openexchange.admin.soap.group.soap.NoSuchGroupException noSuchGroupException;

    public NoSuchGroupException_Exception() {
        super();
    }

    public NoSuchGroupException_Exception(String message) {
        super(message);
    }

    public NoSuchGroupException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGroupException_Exception(String message, com.openexchange.admin.soap.group.soap.NoSuchGroupException noSuchGroupException) {
        super(message);
        this.noSuchGroupException = noSuchGroupException;
    }

    public NoSuchGroupException_Exception(String message, com.openexchange.admin.soap.group.soap.NoSuchGroupException noSuchGroupException, Throwable cause) {
        super(message, cause);
        this.noSuchGroupException = noSuchGroupException;
    }

    public com.openexchange.admin.soap.group.soap.NoSuchGroupException getFaultInfo() {
        return this.noSuchGroupException;
    }
}
