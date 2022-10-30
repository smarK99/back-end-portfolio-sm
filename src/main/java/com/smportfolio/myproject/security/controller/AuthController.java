package com.smportfolio.myproject.security.controller;

import com.smportfolio.myproject.security.dto.DTOJwt;
import com.smportfolio.myproject.security.dto.LoginUsuario;
import com.smportfolio.myproject.security.dto.NuevoUsuario;
import com.smportfolio.myproject.security.entity.Rol;
import com.smportfolio.myproject.security.entity.Usuario;
import com.smportfolio.myproject.security.enums.RolNombre;
import com.smportfolio.myproject.security.jwt.JwtProvider;
import com.smportfolio.myproject.security.service.RolService;
import com.smportfolio.myproject.security.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    UsuarioService userServ;
    
    @Autowired
    RolService rolServ;
    
    @Autowired
    JwtProvider jwtProvider;
    
    //Creamos un usuario nuevo
    @PostMapping("/nuevo")
    public ResponseEntity<?> newUser(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity("Campo incorrecto o email inválido.", HttpStatus.BAD_REQUEST);
        }
        if(userServ.existsByUsername(nuevoUsuario.getUsername())){
            return new ResponseEntity("Ese nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
        }
        if(userServ.existsByEmail(nuevoUsuario.getEmail())){
            return new ResponseEntity("Ese email ya esta registrado.", HttpStatus.BAD_REQUEST);
        }
        
        Usuario usuario = new Usuario(nuevoUsuario.getName(), nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEmail());
        
        Set<Rol> rolesList = new HashSet<>();
        
        rolesList.add(rolServ.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRolesList().contains("admin")){
            rolesList.add(rolServ.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        
        usuario.setRolesList(rolesList);
        userServ.save(usuario);
        
        return new ResponseEntity("Usuario creado con éxito!", HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<DTOJwt> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity("Campos incorrectos", HttpStatus.BAD_REQUEST);
        }
        
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        String jwt = jwtProvider.generateToken(auth);
        
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        
        DTOJwt dtoJwt = new DTOJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(dtoJwt, HttpStatus.OK);
    }
}
