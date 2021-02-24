package ru.softwarecom.uspn.emulators.ecasa.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс описывает пользователя.
 * <p>
 * Пользователь создается один раз сразу с логином и электронной очередью.
 * Дальнейшее изменение логина и пароля невозможно.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused", "JpaDataSourceORMInspection"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"roles"})
@Entity
@Table(
        schema = "USPNECASA",
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_USER_NAME", columnNames = "name"),
                @UniqueConstraint(name = "UK_USER_USERNAME", columnNames = "username"),
                @UniqueConstraint(name = "UK_USER_EMAIL", columnNames = "email")
        }
)
@SequenceGenerator(
        schema = "USPNECASA",
        sequenceName = "User_ID",
        name = "userIdGenerator"
)
public class EcasaUser implements Serializable, UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userIdGenerator"
    )
    // для эксперимента
    @Setter
    private Long id;

    /**
     * Логин пользователя.
     * Неизменяемый.
     */
    @Size(min = 3, max = 32)
    @Column(nullable = false, updatable = false, unique = true, length = 32)
    private String username;

    /**
     * Имя пользователя
     */
    @Setter
    @NotNull
    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    /**
     * Имя пользователя
     */
    @Setter
    @NotNull
    @Column(nullable = false)
    private String firstName;

    /**
     * Отчество пользователя
     */
    @Setter
    @NotNull
    @Column(nullable = false)
    private String middleName;

    /**
     * Фамилия пользователя
     */
    @Setter
    @NotNull
    @Column(nullable = false)
    private String lastName;

    /**
     * Электронная почта пользователя.
     * Неизменяемая.
     */
    @NotNull
    @Email
    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    /**
     * Описание пользователя.
     * Может быть изменено.
     */
    @Setter
    @NotNull
    @Column(nullable = false)
    private String description;

    /**
     * Пароль пользователя.
     */
    @Setter
    private String password;

    /**
     * Признак "годности" пользователя.
     */
    @Setter
    private boolean expired = false;

    /**
     * Признак блокировки пользователя.
     */
    @Setter
    private boolean locked = false;

    /**
     * Признак "годности" пароля пользователя.
     */
    @Setter
    private boolean credentialsExpired = false;

    /**
     * Признак включенности пользователя.
     */
    @Setter
    private boolean enabled = true;

    /**
     * Список ролей пользователя.
     * Напрямую нельзя изменять список ролей.
     * Для изменения списка ролей используйте метод {@code addRole(Role)}.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "USPNECASA",
            name = "user_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"),
            foreignKey = @ForeignKey(name = "FK_ROLE_USER_ID"),
            inverseForeignKey = @ForeignKey(name = "FK_USER_ROLE_ID")
    )
    private Set<EcasaRole> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user")
//    private Set<Session> sessions = new HashSet<>();

    public EcasaUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Для защиты список ролей обернут в неизменяемую коллекцию.
     *
     * @return список ролей.
     */
    public Set<EcasaRole> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Данный метод добавляет роль к пользователю.
     *
     * @param role добавляемая роль.
     * @return текущий пользователь.
     */
    @SuppressWarnings("UnusedReturnValue")
    public EcasaUser addRole(EcasaRole role) {
        this.roles.add(role);
        return this;
    }

    public EcasaUser addRoles(Collection<EcasaRole> roles) {
        this.roles.addAll(roles);
        return this;
    }

    public EcasaUser removeRole(EcasaRole role) {
        this.roles.remove(role);
        return this;
    }

    public EcasaUser clearRoles() {
        this.roles.clear();
        return this;
    }

//    /**
//     * Для защиты список сессий обернут в неизменяемую коллекцию.
//     *
//     * @return список ролей.
//     */
//    public Set<Session> getSessions() {
//        return Collections.unmodifiableSet(sessions);
//    }
//
//    /**
//     * Данный метод добавляет сессию к пользователю.
//     *
//     * @param session добавляемая роль.
//     * @return текущий пользователь.
//     */
//    @SuppressWarnings("UnusedReturnValue")
//    public EcasaUser addSession(Session session) {
//        session.setUser(this);
//        this.sessions.add(session);
//        return this;
//    }

    //////////////////////////////////////////////////
    // Имплементация методов интерфейса UserDetails //
    //////////////////////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }
}
