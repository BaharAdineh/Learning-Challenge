package com.challenge.userservice.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final Logger log = LoggerFactory.getLogger(TokenService.class);
    private final JwtEncoder encoder;
    private final JwtDecoder jwtDecoder;
    public TokenService(JwtEncoder encoder, JwtDecoder jwtDecoder) {
        this.encoder = encoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public boolean validateToken(String token) {
        try {
            jwtDecoder.decode(token);
            return true; // Token is valid
        } catch (ExpiredJwtException ex) {
            log.error("Token has expired: " + ex.getMessage());
            return false; // Token has expired
        } catch (JwtException ex) {
            log.error("Token validation failed: " + ex.getMessage());
            return false; // Token validation failed for some other reason
        }
    }
}