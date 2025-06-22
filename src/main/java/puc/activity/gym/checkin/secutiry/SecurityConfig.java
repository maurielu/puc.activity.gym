package puc.activity.gym.checkin.secutiry;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/public").permitAll() // Allow public access to root and /public
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }

    // --- Configuration for mapping Keycloak roles from JWT to Spring Security Authorities ---
    // This is crucial for @PreAuthorize and hasRole() checks
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // This converter extracts roles from the 'realm_access.roles' claim (Keycloak's default for realm roles)
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);
            // Extract realm roles
            if (jwt.hasClaim("realm_access")) {
                Map<String, Object> realmAccess = jwt.getClaim("realm_access");
                if (realmAccess.containsKey("roles")) {
                    @SuppressWarnings("unchecked")
                    Collection<String> realmRoles = (Collection<String>) realmAccess.get("roles");
                    authorities.addAll(realmRoles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                            .collect(Collectors.toList()));
                }
            }
            // You can also extract client roles if needed (e.g., from 'resource_access.spring-boot-app.roles')
            if (jwt.hasClaim("resource_access")) {
                Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
                if (resourceAccess.containsKey("spring-boot-app")) { // 'spring-boot-app' is your client ID
                    @SuppressWarnings("unchecked")
                    Map<String, Object> clientRolesMap = (Map<String, Object>) resourceAccess.get("spring-boot-app");
                    if (clientRolesMap.containsKey("roles")) {
                        @SuppressWarnings("unchecked")
                        Collection<String> clientRoles = (Collection<String>) clientRolesMap.get("roles");
                        authorities.addAll(clientRoles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                                .collect(Collectors.toList()));
                    }
                }
            }
            return authorities;
        });
        return converter;
    }

    // --- Optional: Customizing OIDC User Service for roles from ID Token (for OAuth2 Client flow) ---
    // This is typically for web applications where you perform an OIDC login and want roles to be
    // available in the Principal for Thymeleaf or session-based access.
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            // Extract realm roles from ID Token claims
            if (oidcUser.hasClaim("realm_access")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> realmAccess = oidcUser.getClaimAsMap("realm_access");
                if (realmAccess != null && realmAccess.containsKey("roles")) {
                    @SuppressWarnings("unchecked")
                    Collection<String> realmRoles = (Collection<String>) realmAccess.get("roles");
                    mappedAuthorities.addAll(realmRoles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                            .collect(Collectors.toSet()));
                }
            }

            // Extract client roles from ID Token claims
            if (oidcUser.hasClaim("resource_access")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> resourceAccess = oidcUser.getClaimAsMap("resource_access");
                if (resourceAccess != null && resourceAccess.containsKey("spring-boot-app")) { // Your client ID
                    @SuppressWarnings("unchecked")
                    Map<String, Object> clientRolesMap = (Map<String, Object>) resourceAccess.get("spring-boot-app");
                    if (clientRolesMap != null && clientRolesMap.containsKey("roles")) {
                        @SuppressWarnings("unchecked")
                        Collection<String> clientRoles = (Collection<String>) clientRolesMap.get("roles");
                        mappedAuthorities.addAll(clientRoles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                                .collect(Collectors.toSet()));
                    }
                }
            }

            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }
}