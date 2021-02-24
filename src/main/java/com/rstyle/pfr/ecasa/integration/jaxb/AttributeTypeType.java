
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for attribute_typeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attribute_typeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_attributeType"/>
 *         &lt;element name="for_policy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="attribute" type="{http://rstyle.com/2014/mcaLoader}attributeType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attribute_typeType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "forPolicy",
    "attribute"
})
public class AttributeTypeType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "for_policy", namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean forPolicy;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<AttributeType> attribute;

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
     * Gets the value of the forPolicy property.
     * 
     */
    public boolean isForPolicy() {
        return forPolicy;
    }

    /**
     * Sets the value of the forPolicy property.
     * 
     */
    public void setForPolicy(boolean value) {
        this.forPolicy = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeType }
     * 
     * 
     */
    public List<AttributeType> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<AttributeType>();
        }
        return this.attribute;
    }

}
