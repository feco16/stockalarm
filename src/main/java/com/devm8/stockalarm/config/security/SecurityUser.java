package com.devm8.stockalarm.config.security;

import com.devm8.stockalarm.model.entity.StockUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {

    private final StockUser stockUser;
    private final String authority;

    public SecurityUser(final StockUser stockUser, final String authority) {
        this.stockUser = stockUser;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return stockUser.getPassword();
    }

    @Override
    public String getUsername() {
        return stockUser.getEmail();
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
}
