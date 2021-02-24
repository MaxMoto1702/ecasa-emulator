package ru.softwarecom.uspn.emulators.ecasa.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EcasaRole.class)
public abstract class EcasaRole_ {

	public static volatile SingularAttribute<EcasaRole, EcasaApplication> application;
	public static volatile SingularAttribute<EcasaRole, String> displayName;
	public static volatile SingularAttribute<EcasaRole, String> name;
	public static volatile SingularAttribute<EcasaRole, String> description;
	public static volatile SingularAttribute<EcasaRole, Long> id;

	public static final String APPLICATION = "application";
	public static final String DISPLAY_NAME = "displayName";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

