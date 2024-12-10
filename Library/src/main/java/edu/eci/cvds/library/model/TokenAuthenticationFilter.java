package edu.eci.cvds.library.model;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.eci.cvds.library.service.AuthorizationService;

import java.io.IOException;
import java.util.List;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthorizationService authorizationService;

    public TokenAuthenticationFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remover "Bearer "
            TokenResponse tokenInfo = authorizationService.validateToken(token);
            
            if (tokenInfo != null && tokenInfo.isValid()) {
                // Correcto, cerrar el paréntesis de la lista de autoridades
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        tokenInfo.getData().getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + tokenInfo.getData().getRole())) // Cerramos correctamente la lista
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response); // Continuar con el siguiente filtro
    }
}

