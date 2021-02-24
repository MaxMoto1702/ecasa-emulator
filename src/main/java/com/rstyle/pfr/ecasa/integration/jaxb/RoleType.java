
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="display_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="description" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="role_category_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType" minOccurs="0"/>
 *         &lt;element name="parent_role_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roleType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "displayName",
    "description",
    "roleCategoryName",
    "parentRoleName"
})
public class RoleType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "display_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String displayName;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String description;
    @XmlElement(name = "role_category_name", namespace = "http://rstyle.com/2014/mcaLoader")
    protected String roleCategoryName;
    @XmlElement(name = "parent_role_name", namespace = "http://rstyle.com/2014/mcaLoader")
    protected String parentRoleName;

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
     * Gets the value of the roleCategoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCategoryName() {
        return roleCategoryName;
    }

    /**
     * Sets the value of the roleCategoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCategoryName(String value) {
        this.roleCategoryName = value;
    }

    /**
     * Gets the value of the parentRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentRoleName() {
        return parentRoleName;
    }

    /**
     * Sets the value of the parentRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentRoleName(String value) {
        this.parentRoleName = value;
    }

}
