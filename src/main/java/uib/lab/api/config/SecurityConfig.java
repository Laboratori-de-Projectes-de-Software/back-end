package uib.lab.api.config;

import uib.lab.api.filter.JwtTokenVerifierFilter;
import uib.lab.api.filter.JwtUsernamePasswordAuthenticationFilter;
import uib.lab.api.handler.AccessDeniedHandler;
import uib.lab.api.service.AuthenticationUserDetailsService;
import uib.lab.api.util.ApiHttpResponse;
import uib.lab.api.util.jwt.JwtAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
    private final ApiHttpResponse apiHttpResponse;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationUserDetailsService authenticationUserDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authenticationUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        JwtUsernamePasswordAuthenticationFilter jwtAuthFilter =
                new JwtUsernamePasswordAuthenticationFilter(objectMapper, apiHttpResponse, authenticationManager());

        jwtAuthFilter.setFilterProcessesUrl("/api/login");
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(jwtAuthFilter)
                .addFilterAfter(
                        new JwtTokenVerifierFilter(jwtAuthenticationProvider, apiHttpResponse),
                        JwtUsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(handler ->
                        handler.authenticationEntryPoint(accessDeniedHandler)
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }
}
