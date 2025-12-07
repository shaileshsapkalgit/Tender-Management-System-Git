package com.fresco.tenderManagement.security;

import com.fresco.tenderManagement.service.LoginService;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;
    private LoginService loginService;

    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {





        
                /*String header = request.getHeader("Authorization");
                String email= null;
                String jwt=null;

                if(header != null && header.startsWith("bearer "))
                {
                    jwt=header.substring(7);
                    email=jwtUtil.getUsernameFromToken(jwt);
                }

                if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                     UserDetails userDetails = loginService.loadUserByUsername(email);

                     if (jwtUtil.validateToken(jwt, userDetails))
                     {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(auth);

                        filterChain.doFilter(request, response);
                        
                     }
                    
                }*/

    }
}
