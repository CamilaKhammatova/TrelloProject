package ru.itis.khammatova.web.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.khammatova.api.services.JwtService;
import ru.itis.khammatova.web.security.details.UserDetailsImpl;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private final JwtService jwtService;

    private UserDetailsImpl userDetails;

    private boolean isAuthenticated;

    private final String token;

    public JwtAuthentication(JwtService jwtService, String token) {
        this.jwtService = jwtService;
        this.token = token;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = (UserDetailsImpl)userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        if (userDetails != null) {
            return userDetails.getUser();
        } else return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return jwtService.nameFromToken(token);
    }
}
