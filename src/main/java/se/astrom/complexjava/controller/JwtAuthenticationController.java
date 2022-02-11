package se.astrom.complexjava.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import se.astrom.complexjava.security.M365LicensesUserDetailsService;
import se.astrom.complexjava.security.jwt.JwtUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private M365LicensesUserDetailsService userDetailsService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, M365LicensesUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
}
