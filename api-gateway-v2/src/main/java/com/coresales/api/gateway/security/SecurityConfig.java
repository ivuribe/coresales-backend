package com.coresales.api.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws
            Exception {
        http
                // Deshabilitar CSRF para API REST
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // Habilitar CORS (Cross-Origin Resource Sharing)
                .cors(cors -> {})
                // Reglas de autorización
                .authorizeExchange(exchange -> exchange
                        // Endpoint público
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/api/auth/**","/actuator/health").permitAll()
                        // El resto requiere autenticacion
                        .anyExchange().authenticated()
                )
                // OAuth2 Resource Server
                // Indicarle a Spring que este gateway funciona como resource server
                // y que los access tokens que recibe con JWT
                .oauth2ResourceServer( oauth2 ->
                        oauth2.jwt(jwt -> {})
                );
        return http.build();
    }
}
