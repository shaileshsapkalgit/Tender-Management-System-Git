package com.fresco.tenderManagement.security;

import com.fresco.tenderManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String email = null; // Renamed username to email for clarity since you use email as username
        String jwt = null;

        // 1. Extract JWT from "Bearer <token>"
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
            // Use JWTUtil to get email (Subject)
            email = jwtUtil.getUsernameFromToken(jwt);
        }

        // 2. Validate token and set authentication context
        // Check if token has an email AND if the user is not already authenticated
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load the full UserDetails object from the database using the email
            UserDetails userDetails = loginService.loadUserByUsername(email);

            // Validate token signature and expiration
            if (jwtUtil.validateToken(jwt, userDetails)) {

                // Create an Authentication object using UserDetails (which includes
                // authorities/roles)
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // CRITICAL STEP: Set the authenticated user in the security context
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 3. Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
