package com.fsd.emart.gateway.filter;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fsd.emart.common.constants.AuthConstants;
import com.fsd.emart.common.exception.AuthException;
import com.fsd.emart.common.util.AuthUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Resource
    private AuthUtil auth;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
                httpHeaders.remove(AuthConstants.HEADER_ID);
                httpHeaders.remove(AuthConstants.HEADER_AUTH);
                String processed = "ng";
                String accountId = "";

                try {
                    String token = httpHeaders.getFirst(AuthConstants.TOKEN_HEADER);
                    if (token != null && token.startsWith(AuthConstants.TOKEN_PREFIX)) {
                        Claims data = (Claims)Jwts.parserBuilder()
                            .setSigningKey(Keys.hmacShaKeyFor(AuthConstants.CRYPT_KEY.getBytes()))
                            .requireExpiration(new Date(System.currentTimeMillis())).build()
                            .parse(token.replace(AuthConstants.TOKEN_PREFIX, "")).getBody();

                        accountId = data.getId();
                        String sessionKey = data.getSubject();

                        auth.froceCheck(accountId, sessionKey);

                    }
                } catch (Exception e) {
                    throw new AuthException("Auth Error!");
                }

                httpHeaders.add(AuthConstants.HEADER_ID, accountId);
                httpHeaders.add(AuthConstants.HEADER_AUTH, processed);
            }).build();

            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        };
    }
}