package com.smportfolio.myproject.service;

import com.smportfolio.myproject.entity.Proyecto;
import com.smportfolio.myproject.repository.IProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectoService {
    
    @Autowired
    IProyectoRepository iProyRep;
    
    public List<Proyecto> List(){
        return iProyRep.findAll();
    }
    
    public Optional<Proyecto> getOne(Long idProyecto){
        return iProyRep.findById(idProyecto);
    }
    
    public Optional<Proyecto> getByNombreProy(String nombreProyecto){
        return iProyRep.findByNombreProyecto(nombreProyecto);
    }
    
    public void save(Proyecto proy){
        iProyRep.save(proy);
    }
    
    public void delete(Long idProy){
        iProyRep.deleteById(idProy);
    }
    
    public boolean existsById(Long idProy){
        return iProyRep.existsById(idProy);
    }
    
    public boolean existsByNombreProy(String nombreProy){
        return iProyRep.existsByNombreProyecto(nombreProy);
    }
    
}
