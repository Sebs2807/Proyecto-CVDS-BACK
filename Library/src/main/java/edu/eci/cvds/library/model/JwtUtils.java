package edu.eci.cvds.library.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

import edu.eci.cvds.library.exceptions.AccessDeniedException;

public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = getSecretKey();
            Jwts.parser().decryptWith(key).build().parse(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            throw new AccessDeniedException("JWT was expired or incorrect");
        } catch (ExpiredJwtException e) {
            throw new AccessDeniedException("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            throw new AccessDeniedException("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new AccessDeniedException("JWT token compact of handler are invalid.");
        }
    }
}