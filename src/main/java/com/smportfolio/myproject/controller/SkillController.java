package com.smportfolio.myproject.controller;

import com.smportfolio.myproject.dto.DTOSkill;
import com.smportfolio.myproject.entity.Skill;
import com.smportfolio.myproject.service.ImpSkillService;
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
@RequestMapping("/skill")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
    
    @Autowired
    ImpSkillService impSkillServ;
    
    //Trae todas las skills
    @GetMapping("/list")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> skillList = impSkillServ.List();
        return new ResponseEntity(skillList, HttpStatus.OK);
    }
    
     //Crea una nueva skill
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DTOSkill dtoSkill){
        if(StringUtils.isBlank(dtoSkill.getNombreSkill())){
            //MENSAJE lo importa desde security.controller que no se en que video lo muestra??
            return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if(impSkillServ.existsByNombreSkill(dtoSkill.getNombreSkill())){
            return new ResponseEntity("Esa skill ya existe", HttpStatus.BAD_REQUEST);
        }
        
        //Si no hay ningun problema, se crea la skill
        Skill skill = new Skill(dtoSkill.getNombreSkill(), dtoSkill.getPorcentaje());
        
        impSkillServ.save(skill);
         
        return new ResponseEntity("Skill agregada exitosamente", HttpStatus.OK);
    }
    
    //Modificar Skill
    @PutMapping("/update/{idSkill}")
    public ResponseEntity<?> update(@PathVariable("idSkill") Long idSkill, @RequestBody DTOSkill dtoSkill){
        //Verificamos que exista la skill
        if (!impSkillServ.existsById(idSkill)) {
            return new ResponseEntity("La skill solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        //Verifica si el nombre nuevo que se ingreso es de una skill ya existente
        if(impSkillServ.existsByNombreSkill(dtoSkill.getNombreSkill()) && impSkillServ.getByNombreSkill(dtoSkill.getNombreSkill()).get().getIdSkill() != idSkill){
            return new ResponseEntity("Esa skill ya existe", HttpStatus.BAD_REQUEST);
        }
        //EL nombre no puede ser nulo o en blanco
        if(StringUtils.isBlank(dtoSkill.getNombreSkill())){
            return new ResponseEntity("El nombre es obligatorio!", HttpStatus.BAD_REQUEST);
        }
        
        //Modificamos la skill
        Skill skill = impSkillServ.getOne(idSkill).get();
        skill.setNombreSkill(dtoSkill.getNombreSkill());
        skill.setPorcentaje(dtoSkill.getPorcentaje());
        
        impSkillServ.save(skill);
        
        return new ResponseEntity("Skill actualizada exitosamente", HttpStatus.OK);
    }
    
    //Borrar Skilleriencia
    @DeleteMapping("/delete/{idSkill}")
    public ResponseEntity<?> delete(@PathVariable("idSkill") Long idSkill){
        //Validamos que el id existe
        if(!impSkillServ.existsById(idSkill)){
            return new ResponseEntity("La skill solicitada no existe", HttpStatus.BAD_REQUEST);
        }
        
        impSkillServ.delete(idSkill);
        
        return new ResponseEntity("Skill eliminada", HttpStatus.OK);
    }
    
    @GetMapping("/detail/{idSkill}")
    public ResponseEntity<Skill> getById(@PathVariable("idSkill") Long idSkill){
        if(!impSkillServ.existsById(idSkill)){
            return new ResponseEntity("No existe el ID", HttpStatus.BAD_REQUEST);
        }
        
        Skill skill = impSkillServ.getOne(idSkill).get();
        
        return new ResponseEntity(skill, HttpStatus.OK);
    }
}
