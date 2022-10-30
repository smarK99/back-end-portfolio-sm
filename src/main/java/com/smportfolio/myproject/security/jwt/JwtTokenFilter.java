package com.smportfolio.myproject.security.jwt;

import com.smportfolio.myproject.security.service.ImpUserDetails;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter{//Vuelve a validar el token
    
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    ImpUserDetails impUserDetailsServ;
    
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            return header.replace("Bearer", "");
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request); 
            if(token != null && jwtProvider.validateToken(token)){
                String userName = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = impUserDetailsServ.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(UsernameNotFoundException err){
            logger.error("Fallo el metodo doFilterInternal: JwtTokenFilter.java");
        }
        filterChain.doFilter(request, response);
    }
    
}
