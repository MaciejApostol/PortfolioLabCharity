package pl.coderslab.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import pl.coderslab.validator.*;
import pl.coderslab.validator.groups.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "deleted"}),
        @UniqueConstraint(columnNames = {"email", "deleted"})})
@PasswordConfirmed(groups = {Registration.class, NewPassword.class, EditUser.class})
@EmailAndPassword(groups = {Registration.class, EditUser.class}, firstField = "", secondField = "")
@UserIsAlreadyAdmin(groups = NewAdmin.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, groups = {Registration.class, NewAdmin.class, EditUser.class})
    private String username;

    @Column(nullable = false, length = 60)
    @Email(groups = {Registration.class, EditUser.class})
    @NotNull(groups = {Registration.class, EditUser.class})
    @NotBlank(groups = {Registration.class, EditUser.class})
    @EmailNotFound(groups = {Login.class, PasswordReset.class, NewAdmin.class})
    private String email;

        @NotNull(groups = {Registration.class, Login.class, NewPassword.class})//, EditUser.class})
    @NotBlank(groups = {Registration.class, Login.class, NewPassword.class})//, EditUser.class})
        @Size(min = 3, groups = {Registration.class, Login.class, NewPassword.class})//min = 8
    @Password(groups = {Registration.class, NewPassword.class, EditUser.class})
    private String password;

    @Transient
    @NotNull(groups = {Registration.class, NewPassword.class})//, EditUser.class})
    @NotBlank(groups = {Registration.class, NewPassword.class})//, EditUser.class})
    @Size(min = 3, groups = {Registration.class, NewPassword.class, EditUser.class})
    private String passwordConfirmation;

    @NotNull
    private UserAvailability enabled;

    @NotNull
    private Deleted deleted;

    @NotNull(groups = Authentication.class)
    @NotBlank(groups = Authentication.class)
    @Token(groups = Authentication.class)
    private String token;

    @Enumerated(EnumType.ORDINAL)
    private TokenAvailability tokenAvailability;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh.mm")
    @CreationTimestamp
    private LocalDateTime tokenDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public UserAvailability getEnabled() {
        return enabled;
    }

    public void setEnabled(UserAvailability enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenAvailability getTokenAvailability() {
        return tokenAvailability;
    }

    public void setTokenAvailability(TokenAvailability tokenAvailability) {
        this.tokenAvailability = tokenAvailability;
    }

    public LocalDateTime getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(LocalDateTime tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public void setDeleted(Deleted deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", enabled=" + enabled +
                ", deleted=" + deleted +
                ", token='" + token + '\'' +
                ", tokenAvailability=" + tokenAvailability +
                ", tokenDate=" + tokenDate +
                ", roles=" + roles +
                '}';
    }
}
