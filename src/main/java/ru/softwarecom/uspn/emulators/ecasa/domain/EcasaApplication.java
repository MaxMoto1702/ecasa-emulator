package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@Entity
@Immutable
@Table(
        schema = "USPNECASA",
        name = "APPLICATION",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_APPLICATION_NAME", columnNames = "name")
        }
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "APPLICATION_ID",
        name = "applicationIdGenerator"
)
public class EcasaApplication implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicationIdGenerator"
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
    protected boolean regional;

    public EcasaApplication(
            @NonNull String name,
            @NonNull String displayName,
            @NonNull String description,
            boolean regional
    ) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.regional = regional;
    }
}
