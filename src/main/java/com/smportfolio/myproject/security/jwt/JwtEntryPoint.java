package com.smportfolio.myproject.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{//Esta clase chequea si existe un token valido
    
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Falló el metodo commence: security.jwt.jwtEntryPoint");
        //response.sendError(HttpServletResponse.SC_OK);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    
}
