package com.openexchange.admin.soap.reseller.service.reseller.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:54:37.439+02:00
 * Generated source version: 2.6.0
 *
 */
@WebService(targetNamespace = "http://soap.reseller.admin.openexchange.com", name = "OXResellerServicePortType")
@XmlSeeAlso({ObjectFactory.class, com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.rmi.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.rmi.exceptions.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.soap.dataobjects.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.reseller.rmi.exceptions.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.rmi.dataobjects.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.reseller.soap.dataobjects.ObjectFactory.class, com.openexchange.admin.soap.reseller.service.io.ObjectFactory.class})
public interface OXResellerServicePortType {

    //@Oneway
    @Action(input = "urn:updateDatabaseRestrictions", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:updateDatabaseRestrictionsInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:updateDatabaseRestrictionsStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:updateDatabaseRestrictionsRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:updateDatabaseRestrictionsOXResellerException")})
    @RequestWrapper(localName = "updateDatabaseRestrictions", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.UpdateDatabaseRestrictions")
    @WebMethod(action = "urn:updateDatabaseRestrictions")
    public void updateDatabaseRestrictions(
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:change", output = "urn:changeResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:changeInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:changeStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:changeRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:changeOXResellerException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:changeInvalidDataException")})
    @WebMethod(action = "urn:change")
    public void change(
        @WebParam(partName = "parameters", name = "change", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        Change parameters
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception, InvalidDataException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:getAvailableRestrictions", output = "urn:getAvailableRestrictionsResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:getAvailableRestrictionsInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:getAvailableRestrictionsStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:getAvailableRestrictionsRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:getAvailableRestrictionsOXResellerException")})
    @RequestWrapper(localName = "getAvailableRestrictions", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetAvailableRestrictions")
    @WebMethod(action = "urn:getAvailableRestrictions")
    @ResponseWrapper(localName = "getAvailableRestrictionsResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetAvailableRestrictionsResponse")
    public java.util.List<com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.Restriction> getAvailableRestrictions(
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:removeDatabaseRestrictions", output = "urn:removeDatabaseRestrictionsResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:removeDatabaseRestrictionsInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:removeDatabaseRestrictionsStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:removeDatabaseRestrictionsRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:removeDatabaseRestrictionsOXResellerException")})
    @WebMethod(action = "urn:removeDatabaseRestrictions")
    public void removeDatabaseRestrictions(
        @WebParam(partName = "parameters", name = "removeDatabaseRestrictions", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        RemoveDatabaseRestrictions parameters
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:delete", output = "urn:deleteResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:deleteInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:deleteStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:deleteRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:deleteOXResellerException")})
    @WebMethod(action = "urn:delete")
    public void delete(
        @WebParam(partName = "parameters", name = "delete", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        Delete parameters
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:list", output = "urn:listResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:listInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:listStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:listRemoteException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:listInvalidDataException")})
    @RequestWrapper(localName = "list", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.List")
    @WebMethod(action = "urn:list")
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.ListResponse")
    public java.util.List<com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin> list(
        @WebParam(name = "search_pattern", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        java.lang.String searchPattern,
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, InvalidDataException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:getRestrictionsFromContext", output = "urn:getRestrictionsFromContextResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:getRestrictionsFromContextInvalidCredentialsException"), @FaultAction(className = DuplicateExtensionException_Exception.class, value = "urn:getRestrictionsFromContextDuplicateExtensionException"), @FaultAction(className = StorageException_Exception.class, value = "urn:getRestrictionsFromContextStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:getRestrictionsFromContextRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:getRestrictionsFromContextOXResellerException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:getRestrictionsFromContextInvalidDataException")})
    @RequestWrapper(localName = "getRestrictionsFromContext", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetRestrictionsFromContext")
    @WebMethod(action = "urn:getRestrictionsFromContext")
    @ResponseWrapper(localName = "getRestrictionsFromContextResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetRestrictionsFromContextResponse")
    public java.util.List<com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.Restriction> getRestrictionsFromContext(
        @WebParam(name = "ctx", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.reseller.soap.dataobjects.ResellerContext ctx,
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, DuplicateExtensionException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception, InvalidDataException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:create", output = "urn:createResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:createInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:createStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:createRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:createOXResellerException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:createInvalidDataException")})
    @RequestWrapper(localName = "create", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.Create")
    @WebMethod(action = "urn:create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.CreateResponse")
    public com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin create(
        @WebParam(name = "adm", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin adm,
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception, InvalidDataException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:getMultipleData", output = "urn:getMultipleDataResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:getMultipleDataInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:getMultipleDataStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:getMultipleDataRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:getMultipleDataOXResellerException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:getMultipleDataInvalidDataException")})
    @RequestWrapper(localName = "getMultipleData", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetMultipleData")
    @WebMethod(action = "urn:getMultipleData")
    @ResponseWrapper(localName = "getMultipleDataResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetMultipleDataResponse")
    public java.util.List<com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin> getMultipleData(
        @WebParam(name = "admins", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        java.util.List<com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin> admins,
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception, InvalidDataException_Exception;

    @WebResult(name = "return", targetNamespace = "http://soap.reseller.admin.openexchange.com")
    @Action(input = "urn:getData", output = "urn:getDataResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:getDataInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:getDataStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:getDataRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:getDataOXResellerException"), @FaultAction(className = InvalidDataException_Exception.class, value = "urn:getDataInvalidDataException")})
    @RequestWrapper(localName = "getData", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetData")
    @WebMethod(action = "urn:getData")
    @ResponseWrapper(localName = "getDataResponse", targetNamespace = "http://soap.reseller.admin.openexchange.com", className = "com.openexchange.admin.soap.reseller.service.reseller.soap.GetDataResponse")
    public com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin getData(
        @WebParam(name = "adm", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.reseller.rmi.dataobjects.ResellerAdmin adm,
        @WebParam(name = "creds", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        com.openexchange.admin.soap.reseller.service.rmi.dataobjects.Credentials creds
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception, InvalidDataException_Exception;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:updateDatabaseModuleAccessRestrictions", output = "urn:updateDatabaseModuleAccessRestrictionsResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:updateDatabaseModuleAccessRestrictionsInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:updateDatabaseModuleAccessRestrictionsStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:updateDatabaseModuleAccessRestrictionsRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:updateDatabaseModuleAccessRestrictionsOXResellerException")})
    @WebMethod(action = "urn:updateDatabaseModuleAccessRestrictions")
    public void updateDatabaseModuleAccessRestrictions(
        @WebParam(partName = "parameters", name = "updateDatabaseModuleAccessRestrictions", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        UpdateDatabaseModuleAccessRestrictions parameters
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Action(input = "urn:initDatabaseRestrictions", output = "urn:initDatabaseRestrictionsResponse", fault = {@FaultAction(className = InvalidCredentialsException_Exception.class, value = "urn:initDatabaseRestrictionsInvalidCredentialsException"), @FaultAction(className = StorageException_Exception.class, value = "urn:initDatabaseRestrictionsStorageException"), @FaultAction(className = RemoteException_Exception.class, value = "urn:initDatabaseRestrictionsRemoteException"), @FaultAction(className = OXResellerException_Exception.class, value = "urn:initDatabaseRestrictionsOXResellerException")})
    @WebMethod(action = "urn:initDatabaseRestrictions")
    public void initDatabaseRestrictions(
        @WebParam(partName = "parameters", name = "initDatabaseRestrictions", targetNamespace = "http://soap.reseller.admin.openexchange.com")
        InitDatabaseRestrictions parameters
    ) throws InvalidCredentialsException_Exception, StorageException_Exception, RemoteException_Exception, OXResellerException_Exception;
}