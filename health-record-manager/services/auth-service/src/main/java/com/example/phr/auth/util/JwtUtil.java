package com.example.phr.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    private final Key key;
    private static final long EXP_MS = 86400000; // 1 day

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateToken(Authentication auth) {
        var now = Date.from(Instant.now());
        var exp = Date.from(Instant.now().plusMillis(EXP_MS));
        return Jwts.builder()
            .setSubject(auth.getName())
            .claim("roles", auth.getAuthorities())
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Optional<Jws<Claims>> parseToken(String token) {
        try {
            return Optional.of(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token));
        } catch (JwtException e) {
            return Optional.empty();
        }
    }

    public Authentication getAuthentication(Jws<Claims> jwt) {
        var username = jwt.getBody().getSubject();
        var roles = ((List<?>) jwt.getBody().get("roles")).stream()
            .map(Object::toString)
            .map(r -> new SimpleGrantedAuthority(r))
            .toList();
        return new UsernamePasswordAuthenticationToken(username, null, roles);
    }
}
