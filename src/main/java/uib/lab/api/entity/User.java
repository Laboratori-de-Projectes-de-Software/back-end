package uib.lab.api.entity;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class User implements UserDetails {
    private static final long serialVersionUID = -1246018760540645731L;

    @Getter
    public enum Role {
        USER;

        private final GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + name());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String name;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Bot> bots;

    private boolean enabled;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(long id, String u, String n, String p){
        this.id = id;
        this.username = u;
        this.name = n; 
        this.password = p;
    }

    public User(String u, String n, String p){
        this.username = u;
        this.name = n; 
        this.password = p;
    }

    public User(){

    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Role::getAuthority).collect(Collectors.toSet());
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
}
