package se.astrom.complexjava.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import se.astrom.complexjava.exception.AppJwtException;
import se.astrom.complexjava.security.M365LicensesUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private M365LicensesUserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    public JwtRequestFilter(M365LicensesUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException{
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = "";
        String jwt = "";

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwt = requestTokenHeader.substring(7);
            try{
                username = jwtUtil.getUsernameFromToken(jwt);
            }catch (IllegalArgumentException e){
                throw new AppJwtException("Unable to get token: " + e.getMessage());
            }catch(ExpiredJwtException e){
                throw new AppJwtException("Token expired: " + e.getMessage());
            }
        }else{
            throw new AppJwtException("Malformed Authorization header.");
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwt, userDetails)){
                var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
            }
        }
        chain.doFilter(request, response);
    }
}
