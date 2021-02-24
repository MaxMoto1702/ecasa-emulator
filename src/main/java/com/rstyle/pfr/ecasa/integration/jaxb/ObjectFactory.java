
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the msk.security.ecasa.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Application_QNAME = new QName("http://rstyle.com/2014/mcaLoader", "application");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: msk.security.ecasa.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicationType }
     * 
     */
    public ApplicationType createApplicationType() {
        return new ApplicationType();
    }

    /**
     * Create an instance of {@link ObligationAttributeType }
     * 
     */
    public ObligationAttributeType createObligationAttributeType() {
        return new ObligationAttributeType();
    }

    /**
     * Create an instance of {@link ResourceActionsType }
     * 
     */
    public ResourceActionsType createResourceActionsType() {
        return new ResourceActionsType();
    }

    /**
     * Create an instance of {@link AttributeValueType }
     * 
     */
    public AttributeValueType createAttributeValueType() {
        return new AttributeValueType();
    }

    /**
     * Create an instance of {@link FilteredResourcesActionsType }
     * 
     */
    public FilteredResourcesActionsType createFilteredResourcesActionsType() {
        return new FilteredResourcesActionsType();
    }

    /**
     * Create an instance of {@link AttributeType }
     * 
     */
    public AttributeType createAttributeType() {
        return new AttributeType();
    }

    /**
     * Create an instance of {@link PolicyType }
     * 
     */
    public PolicyType createPolicyType() {
        return new PolicyType();
    }

    /**
     * Create an instance of {@link RegionType }
     * 
     */
    public RegionType createRegionType() {
        return new RegionType();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link RoleCategoryType }
     * 
     */
    public RoleCategoryType createRoleCategoryType() {
        return new RoleCategoryType();
    }

    /**
     * Create an instance of {@link AttributeTypeType }
     * 
     */
    public AttributeTypeType createAttributeTypeType() {
        return new AttributeTypeType();
    }

    /**
     * Create an instance of {@link ObligationValueType }
     * 
     */
    public ObligationValueType createObligationValueType() {
        return new ObligationValueType();
    }

    /**
     * Create an instance of {@link ResourceTypeType }
     * 
     */
    public ResourceTypeType createResourceTypeType() {
        return new ResourceTypeType();
    }

    /**
     * Create an instance of {@link ConditionAttributeType }
     * 
     */
    public ConditionAttributeType createConditionAttributeType() {
        return new ConditionAttributeType();
    }

    /**
     * Create an instance of {@link ObligationTypeType }
     * 
     */
    public ObligationTypeType createObligationTypeType() {
        return new ObligationTypeType();
    }

    /**
     * Create an instance of {@link EntitlementType }
     * 
     */
    public EntitlementType createEntitlementType() {
        return new EntitlementType();
    }

    /**
     * Create an instance of {@link RoleType }
     * 
     */
    public RoleType createRoleType() {
        return new RoleType();
    }

    /**
     * Create an instance of {@link ObligationType }
     * 
     */
    public ObligationType createObligationType() {
        return new ObligationType();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link ApplicationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rstyle.com/2014/mcaLoader", name = "application")
    public JAXBElement<ApplicationType> createApplication(ApplicationType value) {
        return new JAXBElement<ApplicationType>(_Application_QNAME, ApplicationType.class, null, value);
    }

}
