package com.smportfolio.myproject.security.service;

import com.smportfolio.myproject.security.entity.Usuario;
import com.smportfolio.myproject.security.entity.UsuarioPrincipal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpUserDetails implements UserDetailsService{
    
    @Autowired
    UsuarioService userServ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userServ.getByUsername(username).get();
        return UsuarioPrincipal.build(usuario);
    }
    
    
}
