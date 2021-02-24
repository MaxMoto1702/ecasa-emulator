package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
@Immutable
@Table(
        schema = "USPNECASA",
        name = "ACTION"
)
public class EcasaAction implements Serializable {

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Parent
    @Setter
    protected EcasaPolicy policy;

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_ACTION_RESOURCE_ID")
    )
    protected EcasaResource resource;

    @NonNull
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "ACTION_TYPE_ID",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FK_ACTION_TYPE_ID")
    )
    protected EcasaActionType type;
}
