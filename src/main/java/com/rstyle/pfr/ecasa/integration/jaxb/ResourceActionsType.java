
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for resource_actionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resource_actionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resource_name" type="{http://rstyle.com/2014/mcaLoader}str_nameType"/>
 *         &lt;element name="resource_type_action" type="{http://rstyle.com/2014/mcaLoader}str_resource_type_actionType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resource_actionsType", namespace = "http://rstyle.com/2014/mcaLoader", propOrder = {
    "resourceName",
    "resourceTypeAction"
})
public class ResourceActionsType {

    @XmlElement(name = "resource_name", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected String resourceName;
    @XmlElement(name = "resource_type_action", namespace = "http://rstyle.com/2014/mcaLoader", required = true)
    protected List<String> resourceTypeAction;

    /**
     * Gets the value of the resourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the value of the resourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceName(String value) {
        this.resourceName = value;
    }

    /**
     * Gets the value of the resourceTypeAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceTypeAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceTypeAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getResourceTypeAction() {
        if (resourceTypeAction == null) {
            resourceTypeAction = new ArrayList<String>();
        }
        return this.resourceTypeAction;
    }

}
