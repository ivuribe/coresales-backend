package com.coresales.service.customer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("Inicio del doFilterInternal");
        System.out.println("Authorization: " + authorization);
        //========================================
        // NO EXISTE TOKEN
        //========================================
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("NO EXISTE TOKEN");
            filterChain.doFilter(request, response);
            return;
        }

        //========================================
        // EXTRAER TOKEN
        //========================================
        String token = authorization.substring(7);

        //========================================
        // VALIDAR TOKEN
        //========================================
        if (!jwtService.isTokenValid(token)) {
            System.out.println("TOKEN NO ES VALIDO");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //========================================
        // AUTENTICAR
        //========================================
        var authentication = new UsernamePasswordAuthenticationToken(
                "authenticated-user",
                null,
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_USER"
                        )
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //========================================
        // CONTINUAR
        //========================================
        System.out.println("Continua con la cadena de filtro");
        filterChain.doFilter(request, response);
    }
}
