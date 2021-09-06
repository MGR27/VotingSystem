package ru.graduation.topjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;
import ru.graduation.topjava.HasIdAndEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractEnabledEntity implements HasIdAndEmail {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
//    @JoinColumn(name = "user_id") //https://stackoverflow.com/a/62848296/548473
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Vote> votes;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.isEnabled(), u.getEmail(), u.getPassword(), u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, true, email, password, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, boolean enabled, String email, String password, Date registered, Collection<Role> roles) {
        super(id, name, enabled, registered);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
