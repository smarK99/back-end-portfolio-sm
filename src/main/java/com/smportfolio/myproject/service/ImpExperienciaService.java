package com.smportfolio.myproject.service;

import com.smportfolio.myproject.entity.Experiencia;
import com.smportfolio.myproject.repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional//Mantiene las consistencias entre la bdd y el backend
public class ImpExperienciaService{
    
    @Autowired
    IExperienciaRepository iExpRep;
    
    public List<Experiencia> List(){
        return iExpRep.findAll();
    }
    
    public Optional<Experiencia> getOne(Long idExp){
        return iExpRep.findById(idExp);
    }
    
    public Optional<Experiencia> getByNombreExp(String nombreExp){
        return iExpRep.findByNombreExp(nombreExp);
    }
    
    public void save(Experiencia exp){
        iExpRep.save(exp);
    }
    
    public void delete(Long idExp){
        iExpRep.deleteById(idExp);
    }
    
    public boolean existsById(Long idExp){
        return iExpRep.existsById(idExp);
    }
    
    public boolean existsByNombreExp(String nombreExp){
        return iExpRep.existsByNombreExp(nombreExp);
    }
    
}
