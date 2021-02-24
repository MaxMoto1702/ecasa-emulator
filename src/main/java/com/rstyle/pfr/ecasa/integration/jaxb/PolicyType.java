
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for policyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="policyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="display_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="description" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="deny" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="semantic_and" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="condition" type="{http://rstyle.com/2014/mcaLoader}str_descriptionType" minOccurs="0"/>
 *         &lt;element name="condition_attribute" type="{http://rstyle.com/2014/mcaLoader}condition_attributeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="role_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="entitlement_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resource_actions" type="{http://rstyle.com/2014/mcaLoader}resource_actionsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="filtered_resources_actions" type="{http://rstyle.com/2014/mcaLoader}filtered_resources_actionsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="obligation" type="{http://rstyle.com/2014/mcaLoader}obligationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "policyType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "name",
    "displayName",
    "description",
    "deny",
    "semanticAnd",
    "condition",
    "conditionAttribute",
    "roleName",
    "entitlementName",
    "resourceActions",
    "filteredResourcesActions",
    "obligation"
})
public class PolicyType {

    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String name;
    @XmlElement(name = "display_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String displayName;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String description;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean deny;
    @XmlElement(name = "semantic_and", namespace = "http://rstyle.com/2014/mcaLoader", defaultValue = "false")
    protected boolean semanticAnd;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected String condition;
    @XmlElement(name = "condition_attribute", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<ConditionAttributeType> conditionAttribute;
    @XmlElement(name = "role_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String roleName;
    @XmlElement(name = "entitlement_name", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<String> entitlementName;
    @XmlElement(name = "resource_actions", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<ResourceActionsType> resourceActions;
    @XmlElement(name = "filtered_resources_actions", namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<FilteredResourcesActionsType> filteredResourcesActions;
    @XmlElement(namespace = "http://rstyle.com/2014/mcaLoader")
    protected List<ObligationType> obligation;

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
     * Gets the value of the deny property.
     * 
     */
    public boolean isDeny() {
        return deny;
    }

    /**
     * Sets the value of the deny property.
     * 
     */
    public void setDeny(boolean value) {
        this.deny = value;
    }

    /**
     * Gets the value of the semanticAnd property.
     * 
     */
    public boolean isSemanticAnd() {
        return semanticAnd;
    }

    /**
     * Sets the value of the semanticAnd property.
     * 
     */
    public void setSemanticAnd(boolean value) {
        this.semanticAnd = value;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
    }

    /**
     * Gets the value of the conditionAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditionAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditionAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConditionAttributeType }
     * 
     * 
     */
    public List<ConditionAttributeType> getConditionAttribute() {
        if (conditionAttribute == null) {
            conditionAttribute = new ArrayList<ConditionAttributeType>();
        }
        return this.conditionAttribute;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the entitlementName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entitlementName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntitlementName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEntitlementName() {
        if (entitlementName == null) {
            entitlementName = new ArrayList<String>();
        }
        return this.entitlementName;
    }

    /**
     * Gets the value of the resourceActions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceActions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceActionsType }
     * 
     * 
     */
    public List<ResourceActionsType> getResourceActions() {
        if (resourceActions == null) {
            resourceActions = new ArrayList<ResourceActionsType>();
        }
        return this.resourceActions;
    }

    /**
     * Gets the value of the filteredResourcesActions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filteredResourcesActions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilteredResourcesActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilteredResourcesActionsType }
     * 
     * 
     */
    public List<FilteredResourcesActionsType> getFilteredResourcesActions() {
        if (filteredResourcesActions == null) {
            filteredResourcesActions = new ArrayList<FilteredResourcesActionsType>();
        }
        return this.filteredResourcesActions;
    }

    /**
     * Gets the value of the obligation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obligation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObligation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObligationType }
     * 
     * 
     */
    public List<ObligationType> getObligation() {
        if (obligation == null) {
            obligation = new ArrayList<ObligationType>();
        }
        return this.obligation;
    }

}
