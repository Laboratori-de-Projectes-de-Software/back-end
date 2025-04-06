package uib.lab.api.domain;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uib.lab.api.domain.entity.User.Role;

public class UserDomain implements UserDetails {
    private long id;
    private String mail;
    private String username;
    private String password;
    private boolean isEnabled;
    private Set<Role> roles;

    public UserDomain(long id, String mail, String username, String password, boolean isEnabled, Set<Role> roles) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDomain(long id, String mail, String username, String password, boolean isEnabled) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDomain(String mail, String username, String password, boolean isEnabled, Set<Role> roles) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDomain() {}

    public long getId() {
        return this.id;
    }

    public String getMail() {
        return this.mail;
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

    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }
}
