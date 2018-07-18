package com.example.employeeapi.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

public class Admin implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String fullname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Admin setUsername(String username) {
        this.username = username;
        return this;
    }

    public Admin setPassword(String password) {
        this.password = password;
        //new BCryptPasswordEncoder().encode(password)
        return this;
    }

    public Admin setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
}
