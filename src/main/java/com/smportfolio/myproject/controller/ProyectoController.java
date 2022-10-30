package com.smportfolio.myproject.controller;

import com.smportfolio.myproject.dto.DTOProyecto;
import com.smportfolio.myproject.entity.Proyecto;
import com.smportfolio.myproject.service.ImpProyectoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {
    
    @Autowired
    ImpProyectoService impProyServ;
    
    //Trae todas las proyectos
    @GetMapping("/list")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> proyList = impProyServ.List();
        return new ResponseEntity(proyList, HttpStatus.OK);
    }
    
    //Crea una nueva proyecto
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DTOProyecto dtoProyecto){
        if(StringUtils.isBlank(dtoProyecto.getNombreProyecto())){
            //MENSAJE lo importa desde security.controller que no se en que video lo muestra??
            return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if(impProyServ.existsByNombreProy(dtoProyecto.getNombreProyecto())){
            return new ResponseEntity("Esa proyecto existe", HttpStatus.BAD_REQUEST);
        }
        
        //Si no hay ningun problema, se crea la proyecto
        Proyecto proy = new Proyecto(dtoProyecto.getNombreProyecto(), dtoProyecto.getDescripcionProy(), dtoProyecto.getUrlProy());
        impProyServ.save(proy);
        return new ResponseEntity("Proyecto agregada exitosamente", HttpStatus.OK);
    }
    
    
    //Modificar Proyecto
    @PutMapping("/update/{idProyecto}")
    public ResponseEntity<?> update(@PathVariable("idProy") Long idProyecto, @RequestBody DTOProyecto dtoProyecto){
        //Verificamos que exista la xp
        if (!impProyServ.existsById(idProyecto)) {
            return new ResponseEntity("La proyecto solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        //Verifica si el nombre nuevo que se ingreso es de una proyecto ya existente
        if(impProyServ.existsByNombreProy(dtoProyecto.getNombreProyecto()) && impProyServ.getByNombreProy(dtoProyecto.getNombreProyecto()).get().getIdProyecto() != idProyecto){
            return new ResponseEntity("Esa proyecto ya existe", HttpStatus.BAD_REQUEST);
        }
        //EL nombre no puede ser nulo
        if(StringUtils.isBlank(dtoProyecto.getNombreProyecto())){
            return new ResponseEntity("El nombre es obligatorio!", HttpStatus.BAD_REQUEST);
        }
        
        //Modificamos la proyecto
        Proyecto proy = impProyServ.getOne(idProyecto).get();
        proy.setNombreProyecto(dtoProyecto.getNombreProyecto());
        proy.setDescripcionProy(dtoProyecto.getDescripcionProy());
        
        impProyServ.save(proy);
        
        return new ResponseEntity("Proyecto actualizada exitosamente", HttpStatus.OK);
    }
    
    //Borrar Proyecto
    @DeleteMapping("/delete/{idProyecto}")
    public ResponseEntity<?> delete(@PathVariable("idProyecto") Long idProyecto){
        //Validamos que el id existe
        if(!impProyServ.existsById(idProyecto)){
            return new ResponseEntity("La proyecto solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        
        impProyServ.delete(idProyecto);
        
        return new ResponseEntity("Proyecto eliminada", HttpStatus.OK);
    }
    
    //La manera que utiliza en el video para response entity
    //return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    @GetMapping("/detail/{idProyecto}")
    public ResponseEntity<Proyecto> getById(@PathVariable("idProyecto") Long idProyecto){
        if(!impProyServ.existsById(idProyecto)){
            return new ResponseEntity("No existe el ID", HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proy = impProyServ.getOne(idProyecto).get();
        
        return new ResponseEntity(proy, HttpStatus.OK);
    }
}
