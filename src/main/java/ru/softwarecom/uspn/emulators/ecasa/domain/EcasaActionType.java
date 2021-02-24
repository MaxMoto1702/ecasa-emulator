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
        name = "ACTION_TYPE",
        uniqueConstraints = @UniqueConstraint(name = "UK_ACTION_TYPE_NAME", columnNames = "name")
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "Action_Type_ID",
        name = "actionTypeIdGenerator"
)
public class EcasaActionType implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actionTypeIdGenerator"
    )
    protected Long id;

    @NonNull
    @Column(
            nullable = false,
            unique = true
    )
    protected String name;

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_ACTION_TYPE_APPLICATION_ID")
    )
    protected EcasaApplication application;
}
