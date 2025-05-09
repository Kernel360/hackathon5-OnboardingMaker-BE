package com.example.onboarding.config;

import com.example.onboarding.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
        System.out.println("[DEBUG] getAuthorities 호출: " + role);
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        System.out.println("[DEBUG] getPassword 호출: " + user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("[DEBUG] getUsername 호출: " + user.getEmail());
        return user.getEmail();
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
