package com.example.springsecurityrbacjwt.service;

import com.example.springsecurityrbacjwt.dto.UserDto;
import com.example.springsecurityrbacjwt.exception.UserDetailsException;
import com.example.springsecurityrbacjwt.persistence.domain.Privileges;
import com.example.springsecurityrbacjwt.persistence.domain.Roles;
import com.example.springsecurityrbacjwt.persistence.domain.Users;
import com.example.springsecurityrbacjwt.persistence.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RbacUserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

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
}
