<!-- cSpell:locale ca -->

# Laboratori de Codi 05

## Objectiu

En aquest laboratori aprendràs una forma d'implementar autenticació i autorització
emprant el _framework Spring Boot_.

## Creació del projecte

Com en els laboratoris anteriors, faràs servir Spring Intializr per tal de crear
el projecte punt de partida.

Per això, seleccionarem les següents dependències:
- Spring Web.
- Spring Security.
- Spring Data JPA.
- MySQL Driver.
- Lombok.

> Les dependències _MySL Driver_ y _Spring Data JPA_ es fan servir per comunicar-se amb la BBDD
> a on s'emmagatzemen les credencials dels usuaris.

Genereu el projecte i descomprimiu-lo en local.

## Configuració de la conexió a la BBDD d'usuaris

A continuació, defineix la configuració per tal de connectar a la BBDD local.
Per fer-ho, defineix les següents propietats en el fitxer de configuració _application.properties_:

```yaml
## JPA properties
spring.datasource.url=jdbc:mysql://localhost:3307/userdb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=secret

## Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
```

Si prefereixes emprar una BBDD en memòria per a aquestes proves, definex les següents propietats:

```yaml
## JPA properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

## Enable H2 console (access /h2-console endpoint)
spring.h2.console.enabled=true

## Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
```

Hauràs d'ajustar els valors en funció de la BBDD que facis servir per al desenvolupament.


## Dependències JSON Web Token

En aquest laboratori es fa servir l'estàndard _JSON Web Token_ (JWT)
per a codificar la informació de seguretat dels usuaris.
Per tal de codificar i decodificar el _token_ d'autenticació,
has d'afegir les següents dependències en el fitxer _pom.xml_:

```xml
<dependencies>
  <!-- Dependències existents... -->
  <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
  </dependency>
  <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
  </dependency>
  <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
  </dependency>
</dependencies>
```

## Entitat usuari

Per tal de poder autenticar als usuaris, el Sistema ha de guardar les seves credencials,
així com els seus rols.
Per tant, serà necessari definir l'entitat `User`, per tal d'emmagatzemar aquesta informació:

```java
package es.uib.auth_demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
```

Si executes l'aplicació, hauries de veure que arranca sense problemes i,
a més, crea la taula `users` a la BBDD que hagis configurat.

## Repositori usuari

Per tal de llegir i escriure usuaris a la BBDD,
farem servir el patró habitual en Spring Boot: _repository_.
Un repositori no és més que un _adapter_,
que permet abstraure l'aplicació de la implementació concreta de la BBDD que es fa servir:

```java
package es.uib.auth_demo.repositories;

import es.uib.auth_demo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
```

La implementació de JPA que es fa servir (Hibernate)
implementarà automàticament els mètodes que es defineixin en aquesta interfície,
sempre que segueixin la nomenclatura definida aquí:

https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

En aquest cas, a més dels mètodes estàndard definits en el repositori `CrudRepository`,
s'ha definit un mètode addicional per poder buscar un usuari a partir del seu correu electrònic.

## Detalls de l'usuari

_Spring Boot Security_ es basa en una abstracció anomenada `UserDetails`.
Aquesta abstracció està definida com una interfície, i es l'aplicació la que l'ha d'implementar.
D'aquesta forma, es dona llibertat i flexibilitat a l'aplicació per tal d'implementar
l'obtenció dels usuaris de la forma que més li convengui:

Per fer-ho, modifica l'entitat `Usuari`, per tal que implementi la interfície:
```java
package es.uib.auth_demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
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
```

En aquest exemple es fa servir el camp `email` com a identificador de l'usuari,
però es podria haver definit un camp `username`, per exemple.

## Codificació i decodificació del JWT

Per tal de poder validar les peticions dels usuaris, ens caldrà poder codificar,
decodificar i validar el _token_ que arribarà amb cada petició.

Per això, definim un servei encarregat únicament de fer aquestes comprovacions:

```java
package es.uib.auth_demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

Fixa't que aquesta classe fa servir alguns valors de configuració addicionals,
injectats mitjançant la directiva Spring `@Value`.
Defineix aquests valors al fitxer de configuració _application.properties_:

```yaml
# Change to a secret value in production!
security.jwt.secret-key=594279c8aa3a93c515ee10f8f05b0f7593455d600719c1dbf6d1c1c758001615
# 1h in milliseconds
security.jwt.expiration-time=3600000
```

El valor de la clau secreta és la clau de xifrat i desxifrat,
per la qual cosa és important canviar-lo en producció.
Es poden generar nous valors utilitzant aquest lloc web:

https://www.devglan.com/online-tools/hmac-sha256-online

## Configuració de seguretat

Ja gairebé ho tens tot llest per poder definir la seguretat de l'aplicació!
El següent que hauràs de fer es indicar a _Spring_ com obtenir els diferents
objectes que s'han anat definint fins ara.
Per això, crearàs varies classes de configuració, les quals permeten, entre altres coses, crear
_beans_ i injectar-los en el context, per tal que _Spring_ els pugui injectar allà a on calgui:

```java
package es.uib.auth_demo.configuration;

import es.uib.auth_demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfiguration {

    private final UserRepository userRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```

Aquesta classe defineix els _beans_ que farem servir per configurar el context de seguretat:

```java
package es.uib.auth_demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationConfiguration {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }
}
```

Aquesta classe li indica a _Spring_ com obtenir els detalls d'usuari a partir del seu _username_
(recorda que en aquest exemple hem fet servir el correu com a identificador dels usuaris).
A més, també se li indica com xifrar i desxifrar les paraules clau dels usuaris,
per evitar guardar-les a la BBDD en texte pla.

## Filtre d'autenticació

_Spring Security_ fa servir el patró _chain of responsibility_ per validar, o no,
les peticions del usuaris.
Per tant, el següent que cal fer és definir un nou filtre que sigui capaç
de configurar el context de seguretat s'_Spring_ a partir de peticions que segueixin
l'estàndard JWT:

```java
package es.uib.auth_demo.configuration;

import es.uib.auth_demo.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
```

Aquest filtre reconeix certs tipus de peticions (les que duen un token JWT) i configura
el camp `Authentication` dins el `SecurityContext` d'_Spring_.
En aquest sentit, el filtre actua també com un _adapter_, ja que transforma la informació
continguda en el JWT en el format de _token_ esperat per _Spring Security_.

## Configuració de seguretat

La darrera passa per configurar la seguretat en _Spring_ és definir a quines rutes cal aplicar
seguretat i a quines no.
A més, també cal afegir el filtre que has definit a l'apartat anterior a la cadena
filtres de seguretat:

```java
package es.uib.auth_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
```

Bàsicament, aquesta classe indica a _Spring Security_ que deixi passar totes les peticions
a les rutes del tipus `/auth/**`, pero requereixi un usuari autenticat per a tota la resta.
A més, li indica que afegeixi el filtre `jwtAuthenticationFilter` a la cadena de filtres
de seguretat.

## Rutes i missatges

Ara ja només queda definir les rutes i els missatges necessaris per tal de permetre que els usuaris es puguin registrar i autenticar.

Es defineix el cos per la petició de registrar-se:

```java
package es.uib.auth_demo.api.model;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String email;

    private String password;

    private String fullName;

}
```

El cos de la petició per autenticar-se:

```java
package es.uib.auth_demo.api.model;

import lombok.Data;

@Data
public class LoginUserDto {

    private String email;

    private String password;

}
```

I la resposta a la petició d'autenticació:

```java
package es.uib.auth_demo.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class LoginResponseDto {

    public LoginResponseDto(final String token, final long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    private String token;

    private long expiresIn;

}
```

Finalment, es defineix el controlador que rebrà les peticions de registre i autenticació:

```java
package es.uib.auth_demo.api.controller;

import es.uib.auth_demo.api.model.LoginResponseDto;
import es.uib.auth_demo.api.model.LoginUserDto;
import es.uib.auth_demo.api.model.RegisterUserDto;
import es.uib.auth_demo.entities.User;
import es.uib.auth_demo.services.AuthenticationService;
import es.uib.auth_demo.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }

}
```

Pots provar que la lògica funciona arrancant el servei i enviat una petició de registre,
per exemple, amb la comanda `curl`:

```bash
curl --location 'localhost:8005/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "me@gmail.com",
    "password": "my-secret-password",
    "fullName": "My Full Name"
}'
```

Hauries de veure que el servei retorna una resposta amb codi 200 i les dades del nou usuari
al cos de la resposta.

A continuació, pots fer _log-in_ mitjançat la comanda:

```bash
curl --location 'localhost:8005/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "me@gmail.com",
    "password": "my-secret-password"
}'
```

El servei hauria de respondre amb un codi 200 i el token de l'usuari al cos de la resposta.

## Accedint a recursos privats

Un cop implementada la seguretat, el servei pot definir els controladors
i serveis que siguin necessaris, per tal d'implementar la lògica de negoci.

Qualsevol controlador que estigui a una ruta diferent de `/auth/**` requerirà autenticació,
tal com has configurat en les passes prèvies.

A mode d'exemple, pots implementar el següent controlador:

```java
package es.uib.auth_demo.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello, world!";
    }

    @GetMapping("hello-user")
    public String helloUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
        return "Hello, " + userDetails.getUsername() + "!";
    }

}
```

Com pots veure, no cal definir res, ja que la ruta està securitzada per configuració.

A més, com que el filtre de seguretat s'encarrega de configurar el context de seguretat,
es pot accedir a aquest context per obtenir les dades de l'usuari a partir del _token_.

Si envies la següent petició:

```bash
curl --location 'localhost:8005/api/hello-world'
```

Hauries de rebre un error 403 (Forbidden), ja que estàs intentant accedir a un recurs que requereix autenticació.

En canvi, si afegeixes una capçalera de seguertat amb el token que has rebut a la resposta del _log-in_:

```bash
curl --location 'localhost:8005/api/hello-world' \
--header 'Authorization: xxxx'
```

Hauries de rebre una resposta 200 (OK) amb el missatge `Hello, world!` al cos de la resposta.

Si fas la mateixa prova amb la ruta `hello-user`, hauries de rebre el teu _username_ com a resposta.

## Conclusió

En aquesta sessió has après a:
- Configurar la conexió a una BBDD.
- Definir entitats i recuperar-les de la BBDD.
- Configurar la seguretat en un projecte _Spring Boot_ mitjançant l'estàndard JWT.
