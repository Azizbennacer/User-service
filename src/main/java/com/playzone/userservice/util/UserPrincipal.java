package com.playzone.userservice.util;

import io.jsonwebtoken.Claims;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private final UUID userId;
    private final String email;
    private final String fullName;
    private final String language;
    private final Collection<GrantedAuthority> authorities;

    public UserPrincipal(UUID userId, String email, String fullName, String language,
                         Collection<GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.language = language;
        this.authorities = authorities;
    }

    @SuppressWarnings("unchecked")
    public static UserPrincipal fromClaims(Claims claims) {
        UUID userId = UUID.fromString(claims.get("uid", String.class));
        String email = claims.getSubject();
        String fullName = claims.get("name", String.class);
        String language = claims.get("lang", String.class);
        List<String> roleNames = (List<String>) claims.get("roles");

        List<GrantedAuthority> authorities = (roleNames != null)
            ? roleNames.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            : List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(userId, email,
            fullName != null ? fullName : email,
            language != null ? language : "en",
            authorities);
    }

    public UUID getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getLanguage() { return language; }

    @Override public String getUsername() { return email; }
    @Override public String getPassword() { return null; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
