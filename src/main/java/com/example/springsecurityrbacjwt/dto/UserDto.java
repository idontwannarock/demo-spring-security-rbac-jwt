package com.example.springsecurityrbacjwt.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {

    private static final long serialVersionUID = -8794597883310501145L;
    private String username;
    private String password;
    private Set<String> roles;
    private Set<String> privileges;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<String> privileges) {
        this.privileges = privileges;
    }
}
