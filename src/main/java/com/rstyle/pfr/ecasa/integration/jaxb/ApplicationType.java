package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for applicationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applicationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="display_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="description" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="regional" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="region" type="{http://rstyle.com/2014/mcaLoader}regionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attribute_type" type="{http://rstyle.com/2014/mcaLoader}attribute_typeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="role_category" type="{http://rstyle.com/2014/mcaLoader}role_categoryType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="role" type="{http://rstyle.com/2014/mcaLoader}roleType" maxOccurs="unbounded"/>
 *         &lt;element name="resource_type" type="{http://rstyle.com/2014/mcaLoader}resource_typeType" maxOccurs="unbounded"/>
 *         &lt;element name="entitlement" type="{http://rstyle.com/2014/mcaLoader}entitlementType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="policy" type="{http://rstyle.com/2014/mcaLoader}policyType" maxOccurs="unbounded"/>
 *         &lt;element name="obligation_type" type="{http://rstyle.com/2014/mcaLoader}obligation_typeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "displayName",
    "description",
    "regional",
    "region",
    "attributeType",
    "roleCategory",
    "role",
    "resourceType",
    "entitlement",
    "policy",
    "obligationType"
})
@XmlRootElement(name = "application")
public class ApplicationType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "display_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String displayName;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String description;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean regional;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<RegionType> region;
    @XmlElement(name = "attribute_type", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<AttributeTypeType> attributeType;
    @XmlElement(name = "role_category", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<RoleCategoryType> roleCategory;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<RoleType> role;
    @XmlElement(name = "resource_type", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<ResourceTypeType> resourceType;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<EntitlementType> entitlement;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<PolicyType> policy;
    @XmlElement(name = "obligation_type", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<ObligationTypeType> obligationType;

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
     * Gets the value of the regional property.
     * 
     */
    public boolean isRegional() {
        return regional;
    }

    /**
     * Sets the value of the regional property.
     * 
     */
    public void setRegional(boolean value) {
        this.regional = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the region property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegionType }
     * 
     * 
     */
    public List<RegionType> getRegion() {
        if (region == null) {
            region = new ArrayList<RegionType>();
        }
        return this.region;
    }

    /**
     * Gets the value of the attributeType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeTypeType }
     * 
     * 
     */
    public List<AttributeTypeType> getAttributeType() {
        if (attributeType == null) {
            attributeType = new ArrayList<AttributeTypeType>();
        }
        return this.attributeType;
    }

    /**
     * Gets the value of the roleCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleCategoryType }
     * 
     * 
     */
    public List<RoleCategoryType> getRoleCategory() {
        if (roleCategory == null) {
            roleCategory = new ArrayList<RoleCategoryType>();
        }
        return this.roleCategory;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleType }
     * 
     * 
     */
    public List<RoleType> getRole() {
        if (role == null) {
            role = new ArrayList<RoleType>();
        }
        return this.role;
    }

    /**
     * Gets the value of the resourceType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceTypeType }
     * 
     * 
     */
    public List<ResourceTypeType> getResourceType() {
        if (resourceType == null) {
            resourceType = new ArrayList<ResourceTypeType>();
        }
        return this.resourceType;
    }

    /**
     * Gets the value of the entitlement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entitlement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntitlement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntitlementType }
     * 
     * 
     */
    public List<EntitlementType> getEntitlement() {
        if (entitlement == null) {
            entitlement = new ArrayList<EntitlementType>();
        }
        return this.entitlement;
    }

    /**
     * Gets the value of the policy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicyType }
     * 
     * 
     */
    public List<PolicyType> getPolicy() {
        if (policy == null) {
            policy = new ArrayList<PolicyType>();
        }
        return this.policy;
    }

    /**
     * Gets the value of the obligationType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obligationType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObligationType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObligationTypeType }
     * 
     * 
     */
    public List<ObligationTypeType> getObligationType() {
        if (obligationType == null) {
            obligationType = new ArrayList<ObligationTypeType>();
        }
        return this.obligationType;
    }

}
