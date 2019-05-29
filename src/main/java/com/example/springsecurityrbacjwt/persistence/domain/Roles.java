package com.example.springsecurityrbacjwt.persistence.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Users> users;

    @ManyToMany
    @JoinTable(name = "role_privileges",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privileges_id", referencedColumnName = "id"))
    private Collection<Privileges> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public Collection<Privileges> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privileges> privileges) {
        this.privileges = privileges;
    }
}
