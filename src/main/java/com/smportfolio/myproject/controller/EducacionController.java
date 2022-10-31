package com.smportfolio.myproject.controller;

import com.smportfolio.myproject.dto.DTOEducacion;
import com.smportfolio.myproject.entity.Educacion;
import com.smportfolio.myproject.service.ImpEducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {
    
    @Autowired
    ImpEducacionService impEduServ;
    
    //Trae todas las educaciones
    @GetMapping("/list")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> eduList = impEduServ.List();
        return new ResponseEntity(eduList, HttpStatus.OK);
    }
    
    //Crea una nueva educacion
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DTOEducacion dtoEdu){
        if(StringUtils.isBlank(dtoEdu.getNombreEdu())){
            //MENSAJE lo importa desde security.controller que no se en que video lo muestra??
            return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if(impEduServ.existsByNombreEdu(dtoEdu.getNombreEdu())){
            return new ResponseEntity("Esa educación existe", HttpStatus.BAD_REQUEST);
        }
        
        //Si no hay ningun problema, se crea la educacion
        Educacion edu = new Educacion(dtoEdu.getNombreEdu(), dtoEdu.getDescripcionEdu());
        impEduServ.save(edu);
        return new ResponseEntity("Educación agregada exitosamente", HttpStatus.OK);
    }
    
    
    //Modificar Educacion
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idEducacion}")
    public ResponseEntity<?> update(@PathVariable("idEducacion") Long idEducacion, @RequestBody DTOEducacion dtoEdu){
        //Verificamos que exista la edu
        if (!impEduServ.existsById(idEducacion)) {
            return new ResponseEntity("La educación solicitada no existe", HttpStatus.NOT_FOUND);
        }
        //Verifica si el nombre nuevo que se ingreso es de una educacion ya existente
        if(impEduServ.existsByNombreEdu(dtoEdu.getNombreEdu()) && impEduServ.getByNombreEdu(dtoEdu.getNombreEdu()).get().getIdEducacion() != idEducacion){
            return new ResponseEntity("Esa educación ya existe", HttpStatus.BAD_REQUEST);
        }
        //EL nombre no puede ser nulo
        if(StringUtils.isBlank(dtoEdu.getNombreEdu())){
            return new ResponseEntity("El nombre es obligatorio!", HttpStatus.BAD_REQUEST);
        }
        
        //Modificamos la experiencia
        Educacion edu = impEduServ.getOne(idEducacion).get();
        edu.setNombreEdu(dtoEdu.getNombreEdu());
        edu.setDescripcionEdu(dtoEdu.getDescripcionEdu());
        
        impEduServ.save(edu);
        
        return new ResponseEntity("Educación actualizada exitosamente", HttpStatus.OK);
    }
    
    //Borrar Educacion
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idEducacion}")
    public ResponseEntity<?> delete(@PathVariable("idEducacion") Long idEducacion){
        //Validamos que el id existe
        if(!impEduServ.existsById(idEducacion)){
            return new ResponseEntity("La educación solicitada no existe", HttpStatus.NOT_FOUND);
        }
        
        impEduServ.delete(idEducacion);
        
        return new ResponseEntity("Educación eliminada", HttpStatus.OK);
    }
    
    //Detail
    //Trae una educacion
    @GetMapping("/detail/{idEducacion}")
    public ResponseEntity<Educacion> getById(@PathVariable("idEducacion") Long idEducacion){
        if(!impEduServ.existsById(idEducacion)){
            return new ResponseEntity("No existe el ID", HttpStatus.BAD_REQUEST);
        }
        
        Educacion edu = impEduServ.getOne(idEducacion).get();
        
        return new ResponseEntity(edu, HttpStatus.OK);
    }
}
