package com.fresco.tenderManagement.controller;

import com.fresco.tenderManagement.dto.LoginDTO;
import com.fresco.tenderManagement.security.JWTUtil;
import com.fresco.tenderManagement.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

public class LoginController {

    private AuthenticationManager authenticationManager;
    private LoginService loginService;
    private JWTUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser( LoginDTO loginDTO) {
        return null;
    }
}