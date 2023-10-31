package com.example.bankapp.security;

import com.example.bankapp.entity.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;


import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Component
public class Jwtprovider { // генерация и валидация токена, 2 на очереди
    private static final Logger LOGGER = LogManager.getLogger(Jwtprovider.class);
    private final String jwtSecret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"; // ключ, кодовое слово, с помощью которого будут шифроваться токены
    public String generateToken(String email) { // принимает логин // генерация токена
        Date date = Date.from(LocalDateTime.now().plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant()); // указываем, сколько наш токен будет валиден
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // + ключ-токен + модель шифрования
                .compact();
    }

//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException expEx) {
//            LOGGER.error("Token expired");
//        } catch (UnsupportedJwtException unsEx) {
//            LOGGER.error("Unsupported jwt");
//        } catch (MalformedJwtException mjEx) {
//            LOGGER.error("Malformed jwt");
//        } catch (SignatureException sEx) {
//            LOGGER.error("Invalid signature");
//        } catch (Exception e) {
//            LOGGER.error("invalid token");
//        }
//        return false;
//    }

//    public String getEmailFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); //  Claims claims - ?
//        return claims.getSubject();
//    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isTokenValid(String token, CustomUserDetails customUserDetails) {
        final String username = extractUsername(token);
        return (username.equals(customUserDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
