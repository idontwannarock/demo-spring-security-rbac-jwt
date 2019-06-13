package com.example.springsecurityrbacjwt.security;

public class SecurityConstants {

    // Signing key for HS512 algorithm
    public static final String SECRET = "B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdSgVkYp3s6v";

    // JWT token defaults
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final int EXPIRATION_TIME = 24 * 60 * 60; // in seconds

    // Claim keys
    public static final String USERNAME = "username";
    public static final String ROLES = "roles";
    public static final String PRIVILEGES = "privileges";
}
