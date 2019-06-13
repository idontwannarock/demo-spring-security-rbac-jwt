package com.example.springsecurityrbacjwt.persistence.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isEnabled;
    @Column(nullable = false)
    private Boolean isTokenExpired;

    @ManyToMany
    @JoinTable(name = "role_members",
            joinColumns = @JoinColumn(name = "memberId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_role_members_users")),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_role_members_roles")))
    private Set<Roles> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsTokenExpired() {
        return isTokenExpired;
    }

    public void setIsTokenExpired(Boolean isTokenExpired) {
        this.isTokenExpired = isTokenExpired;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
