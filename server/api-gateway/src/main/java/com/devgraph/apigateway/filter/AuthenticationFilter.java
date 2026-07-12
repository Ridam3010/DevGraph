package com.devgraph.apigateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;

@Component
public class AuthenticationFilter implements GlobalFilter {

    // The Gateway needs the EXACT SAME secret key to verify the token!
    @Value("${jwt.secret:local_development_secret_key_that_is_long_enough_for_hs256}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 1. If they are trying to log in or register, let them through without a token!
        if (request.getURI().getPath().contains("/api/v1/auth") || request.getURI().getPath().contains("/api/v1/users/register")) {
            return chain.filter(exchange);
        }

        // 2. For all other routes, check for the "Authorization" header
        if (!request.getHeaders().containsKey("Authorization")) {
            return this.onError(exchange, "Missing Authorization Header");
        }

        String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
        
        // 3. The header should look like "Bearer eyJhbG..."
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " to get just the token
            
            try {
                // 4. Mathematically verify the token using our secret key
                Key key = Keys.hmacShaKeyFor(secret.getBytes());
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                
                // If it doesn't crash, the token is valid! Let them through.
                return chain.filter(exchange);
                
            } catch (Exception e) {
                // The token was fake, expired, or tampered with!
                return this.onError(exchange, "Invalid JWT Token");
            }
        }

        return this.onError(exchange, "Invalid Authorization Header Format");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED); // Returns a 401 Unauthorized error!
        return response.setComplete();
    }
}
