
package com.openexchange.admin.soap.reseller.context.reseller.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f\u00fcr anonymous complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoSuchFilestoreException" type="{http://exceptions.rmi.admin.openexchange.com/xsd}NoSuchFilestoreException" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "noSuchFilestoreException"
})
@XmlRootElement(name = "NoSuchFilestoreException")
public class NoSuchFilestoreException {

    @XmlElement(name = "NoSuchFilestoreException", nillable = true)
    protected com.openexchange.admin.soap.reseller.context.rmi.exceptions.NoSuchFilestoreException noSuchFilestoreException;

    /**
     * Ruft den Wert der noSuchFilestoreException-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link com.openexchange.admin.soap.reseller.context.rmi.exceptions.NoSuchFilestoreException }
     *
     */
    public com.openexchange.admin.soap.reseller.context.rmi.exceptions.NoSuchFilestoreException getNoSuchFilestoreException() {
        return noSuchFilestoreException;
    }

    /**
     * Legt den Wert der noSuchFilestoreException-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link com.openexchange.admin.soap.reseller.context.rmi.exceptions.NoSuchFilestoreException }
     *
     */
    public void setNoSuchFilestoreException(com.openexchange.admin.soap.reseller.context.rmi.exceptions.NoSuchFilestoreException value) {
        this.noSuchFilestoreException = value;
    }

}
