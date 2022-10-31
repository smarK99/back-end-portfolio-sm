package com.smportfolio.myproject.service;

import com.smportfolio.myproject.entity.Persona;
import com.smportfolio.myproject.repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService{
    
    @Autowired
    private IPersonaRepository iPersonaRep;
    
    public List<Persona> List(){
        return iPersonaRep.findAll();
    }
    
    public Optional<Persona> getOne(Long idPersona){
        return iPersonaRep.findById(idPersona);
    }
    
    public Optional<Persona> getByName(String name){
        return iPersonaRep.findByName(name);
    }
    
    public void save(Persona persona){
        iPersonaRep.save(persona);
    }
    
    public void delete(Long idPersona){
        iPersonaRep.deleteById(idPersona);
    }
    
    public boolean existsById(Long idPersona){
        return iPersonaRep.existsById(idPersona);
    }
    
    public boolean existsByName(String name){
        return iPersonaRep.existsByName(name);
    }
   
}
