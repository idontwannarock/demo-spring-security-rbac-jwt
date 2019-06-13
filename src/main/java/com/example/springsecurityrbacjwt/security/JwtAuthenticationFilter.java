package com.example.springsecurityrbacjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.springsecurityrbacjwt.security.SecurityConstants.*;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private ObjectMapper mapper;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper mapper) {
        super(authenticationManager);
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER);
        if (!Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        // 1. Get claims
        Claims claims = getClaimsFrom(token);

        // 2. Extract claims
        String username = claims.get(USERNAME, String.class);
        String rolesJson = claims.get(ROLES, String.class);
        String privilegesJson = claims.get(PRIVILEGES, String.class);

        // 3. Get roles and privileges
        Set<String> roles = readFrom(rolesJson);
        Set<String> privileges = readFrom(privilegesJson);

        // 4. Form principal and authorities
        Set<GrantedAuthority> authorities = Stream.of(roles, privileges).flatMap(Collection::stream).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        User principal = new User(username, "",
                true, true, true, true, authorities);

        // 5. Form UsernamePasswordAuthenticationToken
        if (!Strings.isNullOrEmpty(username)) {
            System.out.println("JWT represents User '" + username + "' with Role: " + authorities);
            return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        }
        return null;
    }

    private Claims getClaimsFrom(final String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(PREFIX, "")).getBody();
        } catch (ExpiredJwtException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse expired JWT : " + token + " failed : " + exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse unsupported JWT : " + token + " failed : " + exception.getMessage());
        } catch (MalformedJwtException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse invalid JWT : " + token + " failed : " + exception.getMessage());
        } catch (SecurityException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse JWT with invalid signature : " + token + " failed : " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse empty or null JWT : " + token + " failed : " + exception.getMessage());
        }
    }

    private <T> Set<T> readFrom(String json) {
        try {
            return mapper.readerFor(mapper.getTypeFactory().constructCollectionType(Set.class, String.class)).readValue(json);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request to parse invalid JWT claims : " + json + " failed : " + e.getMessage());
        }
    }
}
