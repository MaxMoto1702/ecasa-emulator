
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obligation_attributeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obligation_attributeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="attribute_type_name" type="{http://rstyle.com/2014/mcaLoader}str_attributeType"/>
 *         &lt;element name="obligation_attribute_value" type="{http://rstyle.com/2014/mcaLoader}str_nameType" minOccurs="0"/>
 *         &lt;element name="attribute_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obligation_attributeType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "attributeTypeName",
    "obligationAttributeValue",
    "attributeName"
})
public class ObligationAttributeType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "attribute_type_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String attributeTypeName;
    @XmlElement(name = "obligation_attribute_value", namespace = "http://rstyle.com/2014/mcaLoader")
    protected String obligationAttributeValue;
    @XmlElement(name = "attribute_name", namespace = "http://rstyle.com/2014/mcaLoader")
    protected String attributeName;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the attributeTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    /**
     * Sets the value of the attributeTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeTypeName(String value) {
        this.attributeTypeName = value;
    }

    /**
     * Gets the value of the obligationAttributeValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligationAttributeValue() {
        return obligationAttributeValue;
    }

    /**
     * Sets the value of the obligationAttributeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligationAttributeValue(String value) {
        this.obligationAttributeValue = value;
    }

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

}
