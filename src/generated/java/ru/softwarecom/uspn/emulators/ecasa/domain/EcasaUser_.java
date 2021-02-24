package ru.softwarecom.uspn.emulators.ecasa.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EcasaUser.class)
public abstract class EcasaUser_ {

	public static volatile SingularAttribute<EcasaUser, String> lastName;
	public static volatile SetAttribute<EcasaUser, EcasaRole> roles;
	public static volatile SingularAttribute<EcasaUser, String> description;
	public static volatile SingularAttribute<EcasaUser, Boolean> enabled;
	public static volatile SingularAttribute<EcasaUser, String> firstName;
	public static volatile SingularAttribute<EcasaUser, String> password;
	public static volatile SingularAttribute<EcasaUser, Boolean> expired;
	public static volatile SingularAttribute<EcasaUser, String> name;
	public static volatile SingularAttribute<EcasaUser, String> middleName;
	public static volatile SingularAttribute<EcasaUser, Long> id;
	public static volatile SingularAttribute<EcasaUser, Boolean> locked;
	public static volatile SingularAttribute<EcasaUser, Boolean> credentialsExpired;
	public static volatile SingularAttribute<EcasaUser, String> email;
	public static volatile SingularAttribute<EcasaUser, String> username;

	public static final String LAST_NAME = "lastName";
	public static final String ROLES = "roles";
	public static final String DESCRIPTION = "description";
	public static final String ENABLED = "enabled";
	public static final String FIRST_NAME = "firstName";
	public static final String PASSWORD = "password";
	public static final String EXPIRED = "expired";
	public static final String NAME = "name";
	public static final String MIDDLE_NAME = "middleName";
	public static final String ID = "id";
	public static final String LOCKED = "locked";
	public static final String CREDENTIALS_EXPIRED = "credentialsExpired";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

