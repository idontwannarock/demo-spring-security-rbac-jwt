package com.example.springsecurityrbacjwt.service;

import com.example.springsecurityrbacjwt.dto.UserDto;
import com.example.springsecurityrbacjwt.exception.UserDetailsException;
import com.example.springsecurityrbacjwt.persistence.domain.Privileges;
import com.example.springsecurityrbacjwt.persistence.domain.Roles;
import com.example.springsecurityrbacjwt.persistence.domain.Users;
import com.example.springsecurityrbacjwt.persistence.repository.RoleRepository;
import com.example.springsecurityrbacjwt.persistence.repository.UsersRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RbacUserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDto loadUserByUsernameAndPassword(String username, String encodedPassword) throws UserDetailsException {
        Users user = usersRepository.findByUsernameAndPassword(username, encodedPassword);
        if (user == null) {
            throw new UserDetailsException("No user found with name: " + username);
        }
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet()));
        dto.setPrivileges(user.getRoles().stream().flatMap(role -> role.getPrivileges().stream()).map(Privileges::getName).collect(Collectors.toSet()));
        return dto;
    }

    public UserDto saveUserWithUsernameAndPasswordAndRole(String username, String encodedPassword, String roleName) {
        if (usersRepository.existsByUsername(username)) {
            throw new UserDetailsException("Username, " + username + ", has already been registered.");
        }
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setIsEnabled(true);
        user.setIsTokenExpired(false);
        user.setRoles(Sets.newHashSet(roleRepository.findByName(roleName)));
        usersRepository.save(user);
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet()));
        dto.setPrivileges(user.getRoles().stream().flatMap(role -> role.getPrivileges().stream()).map(Privileges::getName).collect(Collectors.toSet()));
        return dto;
    }
}
