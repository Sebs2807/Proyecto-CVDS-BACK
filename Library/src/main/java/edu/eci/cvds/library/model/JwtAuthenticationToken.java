package edu.eci.cvds.library.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal; // El identificador del usuario (e.g., nombre de usuario)
    private final String credentials; // Las credenciales (en este caso no se usan)

    // Constructor que recibe una lista de roles y el sujeto
    public JwtAuthenticationToken(List<String> roles, String principal) {
        super(mapRolesToAuthorities(roles));
        this.principal = principal;
        this.credentials = null;
        setAuthenticated(true); // Establecer la autenticación como válida
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    // Método auxiliar para mapear roles a GrantedAuthorities
    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
