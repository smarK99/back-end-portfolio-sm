package com.smportfolio.myproject.controller;

import com.smportfolio.myproject.dto.DTOExperiencia;
import com.smportfolio.myproject.entity.Experiencia;
import com.smportfolio.myproject.service.ImpExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
    
    @Autowired
    ImpExperienciaService impExpServ;
    
    //Trae todas las experiencias
    @GetMapping("/list")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> xpList = impExpServ.List();
        return new ResponseEntity(xpList, HttpStatus.OK);
    }
    
    //Crea una nueva experiencia
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DTOExperiencia dtoExp){
        if(StringUtils.isBlank(dtoExp.getNombreExp())){
            //MENSAJE lo importa desde security.controller que no se en que video lo muestra??
            return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if(impExpServ.existsByNombreExp(dtoExp.getNombreExp())){
            return new ResponseEntity("Esa experiencia existe", HttpStatus.BAD_REQUEST);
        }
        
        //Si no hay ningun problema, se crea la experiencia
        Experiencia exp = new Experiencia(dtoExp.getNombreExp(), dtoExp.getDescripcionExp());
        impExpServ.save(exp);
        return new ResponseEntity("Experiencia agregada exitosamente", HttpStatus.OK);
    }
    
    
    //Modificar Experiencia
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idExp}")
    public ResponseEntity<?> update(@PathVariable("idExp") Long idExp, @RequestBody DTOExperiencia dtoExp){
        //Verificamos que exista la xp
        if (!impExpServ.existsById(idExp)) {
            return new ResponseEntity("La experiencia solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        //Verifica si el nombre nuevo que se ingreso es de una experiencia ya existente
        if(impExpServ.existsByNombreExp(dtoExp.getNombreExp()) && impExpServ.getByNombreExp(dtoExp.getNombreExp()).get().getIdExp() != idExp){
            return new ResponseEntity("Esa experiencia ya existe", HttpStatus.BAD_REQUEST);
        }
        //EL nombre no puede ser nulo
        if(StringUtils.isBlank(dtoExp.getNombreExp())){
            return new ResponseEntity("El nombre es obligatorio!", HttpStatus.BAD_REQUEST);
        }
        
        //Modificamos la experiencia
        Experiencia exp = impExpServ.getOne(idExp).get();
        exp.setNombreExp(dtoExp.getNombreExp());
        exp.setDescripcionExp(dtoExp.getDescripcionExp());
        
        impExpServ.save(exp);
        
        return new ResponseEntity("Experiencia actualizada exitosamente", HttpStatus.OK);
    }
    
    //Borrar Experiencia
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idExp}")
    public ResponseEntity<?> delete(@PathVariable("idExp") Long idExp){
        //Validamos que el id existe
        if(!impExpServ.existsById(idExp)){
            return new ResponseEntity("La experiencia solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        
        impExpServ.delete(idExp);
        
        return new ResponseEntity("Experiencia eliminada", HttpStatus.OK);
    }
    
    //La manera que utiliza en el video para response entity
    //return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    @GetMapping("/detail/{idExperiencia}")
    public ResponseEntity<Experiencia> getById(@PathVariable("idExperiencia") Long idExperiencia){
        if(!impExpServ.existsById(idExperiencia)){
            return new ResponseEntity("No existe el ID", HttpStatus.BAD_REQUEST);
        }
        
        Experiencia exp = impExpServ.getOne(idExperiencia).get();
        
        return new ResponseEntity(exp, HttpStatus.OK);
    }
}
