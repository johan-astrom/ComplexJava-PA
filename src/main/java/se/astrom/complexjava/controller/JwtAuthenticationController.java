package se.astrom.complexjava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.astrom.complexjava.exception.AppAuthenticationException;
import se.astrom.complexjava.security.M365LicensesUserDetailsService;
import se.astrom.complexjava.security.jwt.JwtRequest;
import se.astrom.complexjava.security.jwt.JwtResponse;
import se.astrom.complexjava.security.jwt.JwtUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private M365LicensesUserDetailsService userDetailsService;
    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, M365LicensesUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthenticationToken() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AppAuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AppAuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
