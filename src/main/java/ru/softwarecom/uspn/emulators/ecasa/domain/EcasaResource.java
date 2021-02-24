package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Immutable
@Table(
        schema = "USPNECASA",
        name = "RESOURCE",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_RESOURCE_NAME", columnNames = "name")
        }
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "RESOURCE_ID",
        name = "resourceIdGenerator"
)
public class EcasaResource implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resourceIdGenerator"
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

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "RESOURCE_TYPE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_RESOURCE_TYPE_ID")
    )
    protected EcasaResourceType type;

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_RESOURCE_APPLICATION_ID")
    )
    protected EcasaApplication application;
}
