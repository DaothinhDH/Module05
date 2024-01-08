package com.ra.security.jwt;

import com.ra.security.user_principle.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UnknownFormatConversionException;

@Component
public class JWTProvider {
    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;
    private final Logger logger = LoggerFactory.getLogger(JWTEntryPoint.class);

    public String generateToken(UserPrinciple userPrinciple) {
        return Jwts.builder().setSubject(userPrinciple.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + EXPIRED)).
                signWith(SignatureAlgorithm.ES256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired Token {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid format {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported Token {}", e.getMessage());
        } catch (SignatureException s) {
            logger.error("Security Exception {}", s.getMessage());
        }
        return false;
    }

    public String getUserNameToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}


