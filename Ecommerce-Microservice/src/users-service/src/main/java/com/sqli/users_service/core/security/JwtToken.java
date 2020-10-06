package com.sqli.users_service.core.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken implements Serializable {
    private RSAKey jwk;
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 1000*60*60*24*5;
    @Value("${jwt.issuer}")
    private String jwt_isser;
    @Value("${jwt.audience}")
    private String jwt_audience;

    public JwtToken() {
        RsaKeyGeneration rsaKeyGeneration=RsaKeyGeneration.getInstance();
        this.jwk = rsaKeyGeneration.getRsaKey();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                .setSigningKey(this.jwk.toPublicKey())
                .parseClaimsJws(token)
                .getBody();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) throws JOSEException {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(a -> {
            roles.add(a.getAuthority());
        });
        claims.put("roles",roles);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) throws JOSEException {
        return Jwts.builder()
            .claim("roles",claims.get("roles"))
            .setSubject(subject)
            .setIssuer(this.jwt_isser)
            .setAudience(this.jwt_audience)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(SignatureAlgorithm.RS256, this.jwk.toRSAPrivateKey())
            .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
