package com.fresco.tenderManagement.security;

import com.fresco.tenderManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        String email = null;
        String jwt = null;

        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
            email = jwtUtil.getUsernameFromToken(jwt);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) // ->(टोकन रिकामे नाहीये && युजर अजून लॉगिन झालेला नाहीये.)
        {
            UserDetails userDetails = loginService.loadUserByUsername(email); //loginService ला सांगतोय->त्या ईमेलवरून डेटाबेस मधून त्या युजरची पूर्ण माहिती (Password, Role, इ.) काढून आण.

            if (jwtUtil.validateToken(jwt, userDetails)) //->इथे आपण चेक करतो की हे टोकन खरे आहे का? ते एक्सपायर (Expired) झाले नाही ना? आणि ते याच युजरचे आहे ना?
            {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());//आता आपल्याला खात्री पटली आहे की युजर बरोबर आहे. म्हणून आपण स्प्रिंग सिक्युरिटीसाठी एक "Official Authentication Object" (ओळखपत्र) बनवतो.<ID Card>
                SecurityContextHolder.getContext().setAuthentication(auth); // आता पूर्ण ॲप्लिकेशनला समजते की "हा युजर आता अधिकृतपणे लॉगिन झाला आहे" आणि त्याला पुढे जाण्याची परवानगी मिळते.
            }
        }

        filterChain.doFilter(request, response);
    }
}
