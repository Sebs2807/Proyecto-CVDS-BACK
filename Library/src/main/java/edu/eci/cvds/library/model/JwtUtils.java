package edu.eci.cvds.library.model;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import edu.eci.cvds.library.exceptions.AccessDeniedException;

/**
 * Clase utilitaria para la validación y manejo de tokens JWT (JSON Web Tokens).
 * Proporciona funcionalidad para validar un token JWT usando una clave secreta.
 */
public class JwtUtils {

    /**
     * Clave secreta utilizada para firmar y validar los tokens JWT.
     * Se inyecta desde las propiedades del sistema o configuración (`application.properties`).
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Convierte la clave secreta en un objeto de tipo `SecretKey`.
     * 
     * @return Clave secreta derivada de la configuración codificada en Base64.
     */
    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Valida un token JWT asegurándose de que sea correcto, no esté expirado, y cumpla con el formato esperado.
     * 
     * @param token El token JWT que se desea validar.
     * @return true si el token es válido; lanza una excepción en caso contrario.
     * @throws AccessDeniedException Si el token no es válido, está expirado o tiene un formato incorrecto.
     */
    public boolean validateToken(String token) {
        try {
            SecretKey key = getSecretKey();
            Jwts.parser().decryptWith(key).build().parse(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new AccessDeniedException("JWT inválido o comprometido.");
        } catch (ExpiredJwtException e) {
            throw new AccessDeniedException("El token JWT ha expirado.");
        } catch (UnsupportedJwtException e) {
            throw new AccessDeniedException("Token JWT no soportado.");
        } catch (IllegalArgumentException e) {
            throw new AccessDeniedException("El token JWT tiene un formato inválido.");
        }
    }
}
