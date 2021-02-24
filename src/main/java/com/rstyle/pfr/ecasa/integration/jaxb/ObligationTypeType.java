
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for obligation_typeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obligation_typeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="display_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="description" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="custom" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="attribute_type_name" type="{http://rstyle.com/2014/mcaLoader}str_attributeType"/>
 *         &lt;element name="obligation_value" type="{http://rstyle.com/2014/mcaLoader}obligation_valueType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obligation_typeType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "displayName",
    "description",
    "custom",
    "attributeTypeName",
    "obligationValue"
})
public class ObligationTypeType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "display_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String displayName;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String description;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean custom;
    @XmlElement(name = "attribute_type_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String attributeTypeName;
    @XmlElement(name = "obligation_value", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<ObligationValueType> obligationValue;

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
     * Gets the value of the custom property.
     * 
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * Sets the value of the custom property.
     * 
     */
    public void setCustom(boolean value) {
        this.custom = value;
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
     * Gets the value of the obligationValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obligationValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObligationValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObligationValueType }
     * 
     * 
     */
    public List<ObligationValueType> getObligationValue() {
        if (obligationValue == null) {
            obligationValue = new ArrayList<ObligationValueType>();
        }
        return this.obligationValue;
    }

}
