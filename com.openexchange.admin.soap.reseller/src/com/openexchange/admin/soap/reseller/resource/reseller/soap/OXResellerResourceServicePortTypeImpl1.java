
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.openexchange.admin.soap.reseller.resource.reseller.soap;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.6.0
 * 2012-06-06T11:10:24.917+02:00
 * Generated source version: 2.6.0
 *
 */

@javax.jws.WebService(
                      serviceName = "OXResellerResourceService",
                      portName = "OXResellerResourceServiceHttpEndpoint",
                      targetNamespace = "http://soap.reseller.admin.openexchange.com",

                      endpointInterface = "com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType")

public class OXResellerResourceServicePortTypeImpl1 implements OXResellerResourceServicePortType {

    private static final Logger LOG = Logger.getLogger(OXResellerResourceServicePortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#change(com.openexchange.admin.soap.reseller.resource.reseller.soap.Change  parameters )*
     */
    @Override
    public void change(Change parameters) throws DatabaseUpdateException_Exception , NoSuchResourceException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation change");
        System.out.println(parameters);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new NoSuchResourceException_Exception("NoSuchResourceException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#delete(com.openexchange.admin.soap.reseller.resource.reseller.soap.Delete  parameters )*
     */
    @Override
    public void delete(Delete parameters) throws DatabaseUpdateException_Exception , NoSuchResourceException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation delete");
        System.out.println(parameters);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new NoSuchResourceException_Exception("NoSuchResourceException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#list(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext  ctx ,)java.lang.String  pattern ,)com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials  auth )*
     */
    @Override
    public java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> list(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext ctx,java.lang.String pattern,com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials auth) throws DatabaseUpdateException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation list");
        System.out.println(ctx);
        System.out.println(pattern);
        System.out.println(auth);
        try {
            java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#create(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext  ctx ,)com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource  res ,)com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials  auth )*
     */
    @Override
    public com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource create(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext ctx,com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource res,com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials auth) throws DatabaseUpdateException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation create");
        System.out.println(ctx);
        System.out.println(res);
        System.out.println(auth);
        try {
            com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#listAll(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext  ctx ,)com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials  auth )*
     */
    @Override
    public java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> listAll(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext ctx,com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials auth) throws DatabaseUpdateException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation listAll");
        System.out.println(ctx);
        System.out.println(auth);
        try {
            java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#getMultipleData(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext  ctx ,)java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource>  resources ,)com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials  auth )*
     */
    @Override
    public java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> getMultipleData(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext ctx,java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> resources,com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials auth) throws DatabaseUpdateException_Exception , NoSuchResourceException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation getMultipleData");
        System.out.println(ctx);
        System.out.println(resources);
        System.out.println(auth);
        try {
            java.util.List<com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new NoSuchResourceException_Exception("NoSuchResourceException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

    /* (non-Javadoc)
     * @see com.openexchange.admin.soap.reseller.resource.reseller.soap.OXResellerResourceServicePortType#getData(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext  ctx ,)com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource  res ,)com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials  auth )*
     */
    @Override
    public com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource getData(com.openexchange.admin.soap.reseller.resource.reseller.soap.dataobjects.ResellerContext ctx,com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource res,com.openexchange.admin.soap.reseller.resource.rmi.dataobjects.Credentials auth) throws DatabaseUpdateException_Exception , NoSuchResourceException_Exception , InvalidCredentialsException_Exception , DuplicateExtensionException_Exception , NoSuchContextException_Exception , StorageException_Exception , RemoteException_Exception , InvalidDataException_Exception    {
        LOG.info("Executing operation getData");
        System.out.println(ctx);
        System.out.println(res);
        System.out.println(auth);
        try {
            com.openexchange.admin.soap.reseller.resource.soap.dataobjects.Resource _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new DatabaseUpdateException_Exception("DatabaseUpdateException...");
        //throw new NoSuchResourceException_Exception("NoSuchResourceException...");
        //throw new InvalidCredentialsException_Exception("InvalidCredentialsException...");
        //throw new DuplicateExtensionException_Exception("DuplicateExtensionException...");
        //throw new NoSuchContextException_Exception("NoSuchContextException...");
        //throw new StorageException_Exception("StorageException...");
        //throw new RemoteException_Exception("RemoteException...");
        //throw new InvalidDataException_Exception("InvalidDataException...");
    }

}
