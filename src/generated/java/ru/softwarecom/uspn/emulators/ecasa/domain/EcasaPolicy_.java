package ru.softwarecom.uspn.emulators.ecasa.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EcasaPolicy.class)
public abstract class EcasaPolicy_ {

	public static volatile SingularAttribute<EcasaPolicy, Boolean> semanticAnd;
	public static volatile SingularAttribute<EcasaPolicy, Boolean> deny;
	public static volatile SingularAttribute<EcasaPolicy, EcasaRole> role;
	public static volatile SingularAttribute<EcasaPolicy, EcasaApplication> application;
	public static volatile SingularAttribute<EcasaPolicy, String> displayName;
	public static volatile SingularAttribute<EcasaPolicy, String> name;
	public static volatile SingularAttribute<EcasaPolicy, String> description;
	public static volatile SingularAttribute<EcasaPolicy, Long> id;
	public static volatile SetAttribute<EcasaPolicy, EcasaAction> actions;

	public static final String SEMANTIC_AND = "semanticAnd";
	public static final String DENY = "deny";
	public static final String ROLE = "role";
	public static final String APPLICATION = "application";
	public static final String DISPLAY_NAME = "displayName";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String ACTIONS = "actions";

}

