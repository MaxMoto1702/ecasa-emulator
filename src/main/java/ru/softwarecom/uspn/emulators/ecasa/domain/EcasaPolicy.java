package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(exclude = {"actions"})
@Entity
@Immutable
@Table(
        schema = "USPNECASA",
        name = "POLICY",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_POLICY_NAME", columnNames = "name"),
                @UniqueConstraint(name = "UK_POLICY_ROLE_ID", columnNames = "role_id")
        }
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "POLICY_ID",
        name = "policyIdGenerator"
)
public class EcasaPolicy implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policyIdGenerator"
    )
    protected Long id;

    @NonNull
    @Column(
            nullable = false,
            unique = true
    )
    protected String name;

    @NonNull
    @Column(nullable = false)
    protected String displayName;

    @NonNull
    @Column(nullable = false)
    protected String description;

    @Column(nullable = false)
    protected boolean deny;

    @Column(nullable = false)
    protected boolean semanticAnd;

    @NonNull
    @OneToOne(
            optional = false,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            unique = true,
            foreignKey = @ForeignKey(name = "FK_POLICY_ROLE_ID")
    )
    protected EcasaRole role;

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_POLICY_APPLICATION_ID")
    )
    protected EcasaApplication application;

    @ElementCollection
    @CollectionTable(
            schema = "USPNECASA",
            name = "ACTION",
            joinColumns = @JoinColumn(name = "POLICY_ID"),
            foreignKey = @ForeignKey(name = "FK_ACTION_POLICY_ID")
    )
    protected Set<EcasaAction> actions = new HashSet<>();

    public EcasaPolicy(
            @NonNull String name,
            @NonNull String displayName,
            @NonNull String description,
            boolean deny,
            boolean semanticAnd,
            @NonNull EcasaRole role,
            @NonNull EcasaApplication application
    ) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.deny = deny;
        this.semanticAnd = semanticAnd;
        this.role = role;
//        this.role.policy = this;
        this.application = application;
    }

    public EcasaPolicy(
            @NonNull String name,
            @NonNull String displayName,
            @NonNull String description,
            boolean deny,
            boolean semanticAnd,
            @NonNull EcasaRole role,
            @NonNull EcasaApplication application,
            Set<EcasaAction> actions
    ) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.deny = deny;
        this.semanticAnd = semanticAnd;
        this.role = role;
//        this.role.policy = this;
        this.application = application;
        this.actions.addAll(actions);
    }
}
