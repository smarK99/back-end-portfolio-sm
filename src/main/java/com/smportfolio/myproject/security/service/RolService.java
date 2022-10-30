package com.smportfolio.myproject.security.service;

import com.smportfolio.myproject.security.entity.Rol;
import com.smportfolio.myproject.security.enums.RolNombre;
import com.smportfolio.myproject.security.repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    
    @Autowired
    IRolRepository iRolRep;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRep.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRolRep.save(rol);
    }
    
    
}
