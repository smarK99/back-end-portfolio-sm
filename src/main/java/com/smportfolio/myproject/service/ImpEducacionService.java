package com.smportfolio.myproject.service;

import com.smportfolio.myproject.entity.Educacion;
import com.smportfolio.myproject.repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {
    
    @Autowired
    IEducacionRepository iEduRep;
    
    //Metodos
    public List<Educacion> List(){
        return iEduRep.findAll();
    }
    
    public Optional<Educacion> getOne(Long idEducacion){
        return iEduRep.findById(idEducacion);
    }
    
    public Optional<Educacion> getByNombreEdu(String nombreEdu){
        return iEduRep.findByNombreEdu(nombreEdu);
    }
    
    public void save(Educacion edu){
        iEduRep.save(edu);
    }
    
    public void delete(Long idEducacion){
        iEduRep.deleteById(idEducacion);
    }
    
    public boolean existsById(Long idEducacion){
        return iEduRep.existsById(idEducacion);
    }
    
    public boolean existsByNombreEdu(String nombreEdu){
        return iEduRep.existsByNombreEdu(nombreEdu);
    }
}
