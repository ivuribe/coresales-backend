package com.coresales.service.user.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

@Configuration
public class AuthorizationServerSettingsConfig {
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                //.issuer("http://localhost:8081")
                .issuer("http://auth.coresales.local:8081")
                .build();
    }
}
