package ru.softwarecom.uspn.emulators.ecasa.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EcasaResource.class)
public abstract class EcasaResource_ {

	public static volatile SingularAttribute<EcasaResource, EcasaApplication> application;
	public static volatile SingularAttribute<EcasaResource, String> displayName;
	public static volatile SingularAttribute<EcasaResource, String> name;
	public static volatile SingularAttribute<EcasaResource, String> description;
	public static volatile SingularAttribute<EcasaResource, Long> id;
	public static volatile SingularAttribute<EcasaResource, EcasaResourceType> type;

	public static final String APPLICATION = "application";
	public static final String DISPLAY_NAME = "displayName";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

