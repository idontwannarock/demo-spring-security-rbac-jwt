package com.example.springsecurityrbacjwt.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDetailsException extends AuthenticationException {

    private static final long serialVersionUID = 7777319218390949112L;

    public UserDetailsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserDetailsException(final String message) {
        super(message);
    }
}
