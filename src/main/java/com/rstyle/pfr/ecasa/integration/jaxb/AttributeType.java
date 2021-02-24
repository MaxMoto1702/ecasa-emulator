
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for attributeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attributeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="display_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="description" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="dynamic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="multiple" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributeType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "displayName",
    "description",
    "dynamic",
    "multiple"
})
public class AttributeType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "display_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String displayName;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String description;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean dynamic;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean multiple;

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
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dynamic property.
     * 
     */
    public boolean isDynamic() {
        return dynamic;
    }

    /**
     * Sets the value of the dynamic property.
     * 
     */
    public void setDynamic(boolean value) {
        this.dynamic = value;
    }

    /**
     * Gets the value of the multiple property.
     * 
     */
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * Sets the value of the multiple property.
     * 
     */
    public void setMultiple(boolean value) {
        this.multiple = value;
    }

}
