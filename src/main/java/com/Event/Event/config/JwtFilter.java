package com.Event.Event.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends UsernamePasswordAuthenticationFilter {

    private final String secretKey = "58th9uerngt98e4ht0erierw0gfn89erwhyt0ern9gher80gthrehg809hh0er8h00h04htr0834ht0"; // Replace with a secure secret key
    private AuthenticationManager authenticationManager;

    // No-args constructor
    public JwtFilter() {
    }

    // Setter for authenticationManager
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        // Skip JWT authentication for reset-password and forgot-password endpoints
        if (path.equals("/api/users/reset-password") || path.equals("/api/users/forgot-password")) {
            chain.doFilter(request, response); // Skip authentication for these paths
            return; // Return to exit the method
        }

        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            // Create authentication object and set it to the SecurityContext
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
                    new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response); // Continue with the filter chain
    }
}
