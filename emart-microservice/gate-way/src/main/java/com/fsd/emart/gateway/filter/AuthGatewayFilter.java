package com.fsd.emart.gateway.filter;

import java.net.URI;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.fsd.emart.gateway.constants.AuthPropertyHandler;
import com.fsd.emart.gateway.constants.GatewayConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class AuthGatewayFilter extends AbstractGatewayFilterFactory<AuthGatewayFilter.Config> {
    public AuthGatewayFilter() {
        super(Config.class);
    }

    @Resource
    private RestTemplate restTemplate;

    private Mono<Void> authError(ServerWebExchange exchange, String err) {
        return setError(exchange, HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> setError(ServerWebExchange exchange, HttpStatus errorType) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorType);
        return response.setComplete();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = HttpHeaders.writableHttpHeaders(request.getHeaders());
            headers.remove(GatewayConstants.HEADER_ID);
            headers.remove(GatewayConstants.HEADER_ROLE);

            // get rule of current path
            String mustAuthRole = AuthPropertyHandler.getProperty(request.getPath().pathWithinApplication().toString());

            if (GatewayConstants.ROLE_ANY.equals(mustAuthRole)) {
                // no check
                return chain.filter(exchange.mutate().request(request).build());
            } else if (mustAuthRole.length() == 0) {
                // unknown path
                return setError(exchange, HttpStatus.NOT_FOUND);
            }

            String token = headers.getFirst(GatewayConstants.TOKEN_HEADER);
            // check token exist
            if (token == null || token.startsWith(GatewayConstants.TOKEN_PREFIX)) {
                return authError(exchange, "No Authorization header.");
            }

            Claims data;
            // verify token
            try {
                data = Jwts.parserBuilder().setAllowedClockSkewSeconds(GatewayConstants.TOKEN_TERM)
                    .setSigningKey(Keys.hmacShaKeyFor(GatewayConstants.CRYPT_KEY.getBytes())).build()
                    .parseClaimsJws(token.replace(GatewayConstants.TOKEN_PREFIX, "")).getBody();
            } catch (Exception e) {
                return authError(exchange, "Invalid Authorization header.");
            }

            // check session exist
            URI uri =
                URI.create(String.format("http://server-auth/login?hid=%s&sss=%s", data.getId(), data.getSubject()));

            @SuppressWarnings("unchecked")
            Map<Object, Object> result = restTemplate.getForObject(uri, Map.class);

            // check role
            if (!mustAuthRole.contains((String)result.get("data"))) {
                return authError(exchange, "Not Authorized to view.");
            }

            // set Authorized header
            headers.add(GatewayConstants.HEADER_ID, data.getId());
            headers.add(GatewayConstants.HEADER_ROLE, (String)result.get("data"));

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {

    }
}
