package com.coresales.api.gateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        System.out.println("Inicialización del FILTER");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        //Endpoints Públicos
        if (isPublicEndpoint(path)){
            return chain.filter(exchange);
        }

        //Autorización
        String authorization = request.getHeaders().getFirst("Authorization");

        //No existe token
        if (authorization == null || !authorization.startsWith("Bearer ")){
            return unauthorized(exchange);
        }

        //Extraer token
        String token = authorization.substring(7);

        //Validar token
        if (!jwtTokenUtil.validateToken(token)){
            return unauthorized(exchange);
        }

        //Continúo con la cadena de filtro
        return chain.filter(exchange);
    }

    private boolean isPublicEndpoint(String path){
        return path.startsWith("api/auth");
    }

    //Respuesta Error 401
    private Mono<Void> unauthorized(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        return exchange.getResponse().setComplete();
    }

    //Prioridad
    @Override
    public int getOrder(){
        return -1;
    }
}
