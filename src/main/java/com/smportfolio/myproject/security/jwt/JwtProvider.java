package com.smportfolio.myproject.security.jwt;

import com.smportfolio.myproject.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {//GEnera el token y valida si esta correctamente armado el mismo
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expiration;
    
    //Generamos el token
    public String generateToken (Authentication auth){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) auth.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime()+ expiration * 1000))
               .signWith(SignatureAlgorithm.HS512, secret)
               .compact();
    }
    
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    //Valida el token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch(MalformedJwtException mal){
            logger.error("Token mal formado.");
        }
        catch(UnsupportedJwtException uns){
            logger.error("Token no soportado.");
        }
        catch(ExpiredJwtException exp){
            logger.error("Token expirado.");
        }
        catch(IllegalArgumentException ill){
            logger.error("Token vacío.");
        }
        catch(SignatureException sign){
            logger.error("Firma no válida.");
        }
        return false;
    }
}
