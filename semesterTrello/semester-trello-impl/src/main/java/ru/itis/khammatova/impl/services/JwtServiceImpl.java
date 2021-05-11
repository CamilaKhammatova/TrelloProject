package ru.itis.khammatova.impl.services;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.khammatova.api.services.JwtService;

import java.util.Date;

@Component
public class JwtServiceImpl implements JwtService {

    private final String SECRET = "superSecret";

    private final Integer EXPIRED_TIME = 3600000;

    public String generateJwtToken(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRED_TIME)).signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


    public String nameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
