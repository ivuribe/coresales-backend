package com.coresales.service.user.auth.config;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Configuration
public class RegisteredClientConfig {
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient vueClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("coresales-web")
                /*
                 * Vue es un cliente público.
                 * No almacenamos client_secret.
                 */
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                /* OAuth 2.0 Authorization Code */
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                /* Vue callback */
                .redirectUri("http://localhost:5173/callback")
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                //.scope("openid")
                /* PKCE */
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(true)
                        .requireAuthorizationConsent(false)
                        .build()
                )
                .build();

        return new InMemoryRegisteredClientRepository(vueClient);
    }
}