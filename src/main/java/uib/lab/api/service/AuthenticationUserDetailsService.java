package uib.lab.api.service;

import uib.lab.api.domain.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailsService implements UserDetailsService {
    private final UserPort userPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)); }
}
