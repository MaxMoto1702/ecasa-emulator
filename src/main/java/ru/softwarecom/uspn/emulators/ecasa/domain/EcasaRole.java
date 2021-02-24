package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(/*exclude = {"policy"}*/)
@Entity
@Immutable
@Table(
        schema = "USPNECASA",
        name = "ROLE",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ROLE_NAME", columnNames = "name")
        }
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "ROLE_ID",
        name = "roleIdGenerator"
)
public class EcasaRole implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roleIdGenerator"
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
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_ROLE_APPLICATION_ID")
    )
    protected EcasaApplication application;

//    @OneToOne(
//            mappedBy = "role",
//            fetch = FetchType.LAZY
//    )
//    protected EcasaPolicy policy;

    ///////////////////////////////////////////////////////
    // Имплементация методов интерфейса GrantedAuthority //
    ///////////////////////////////////////////////////////

    @Override
    public String getAuthority() {
        return "ROLE_" + name.toUpperCase();
    }
}
