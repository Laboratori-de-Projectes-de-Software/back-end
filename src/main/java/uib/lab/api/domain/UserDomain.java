package uib.lab.api.domain;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uib.lab.api.infraestructure.jpaEntity.User.Role;

public class UserDomain implements UserDetails {
    private int id;
    private String mail;
    private String username;
    private String password;
    private boolean isEnabled;
    private Set<Role> roles;
    private int[] botsId;

    public UserDomain(int id, String mail, String username, String password, boolean isEnabled, Set<Role> roles) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDomain(int id, String mail, String username, String password, boolean isEnabled) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public UserDomain(String mail, String username, String password, boolean isEnabled, Set<Role> roles) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDomain() {}

    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public int[] getBotsId() {
        return botsId;
    }

    public void setBotsId(int[] botsId) {
        this.botsId = botsId;
    }
}
