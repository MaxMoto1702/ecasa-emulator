package ru.softwarecom.uspn.emulators.ecasa.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EcasaAction.class)
public abstract class EcasaAction_ {

	public static volatile SingularAttribute<EcasaAction, EcasaResource> resource;
	public static volatile SingularAttribute<EcasaAction, EcasaActionType> type;
	public static volatile SingularAttribute<EcasaAction, EcasaPolicy> policy;

	public static final String RESOURCE = "resource";
	public static final String TYPE = "type";
	public static final String POLICY = "policy";

}

