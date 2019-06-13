package com.example.springsecurityrbacjwt.controller;

import com.example.springsecurityrbacjwt.dto.UserDto;
import com.example.springsecurityrbacjwt.security.JwtUtil;
import com.example.springsecurityrbacjwt.service.RbacUserDetailsService;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private RbacUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "Login with Username and Password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam(required = false, defaultValue = "") String password) {
        String encodedPassword = password.isEmpty() ? "" : passwordEncoder.encode(password);
        UserDto user = userDetailsService.loadUserByUsernameAndPassword(username, encodedPassword);
        Map<String, String> responseBody = Maps.newHashMap();
        responseBody.put("jwt", JwtUtil.createToken(user.getUsername(), user.getRoles(), user.getPrivileges()));
        return ResponseEntity.ok(responseBody);
    }
}
