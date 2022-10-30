package com.smportfolio.myproject.security.service;

import com.smportfolio.myproject.security.entity.Usuario;
import com.smportfolio.myproject.security.repository.IUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    IUsuarioRepository iUserRep;
    
    public Optional<Usuario> getByUsername(String username){
        return iUserRep.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return iUserRep.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email){
        return iUserRep.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        iUserRep.save(usuario);
    }
}
