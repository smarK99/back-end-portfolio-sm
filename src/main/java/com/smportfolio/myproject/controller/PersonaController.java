package com.smportfolio.myproject.controller;

import com.smportfolio.myproject.dto.DTOPersona;
import com.smportfolio.myproject.entity.Persona;
import com.smportfolio.myproject.service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    
    @Autowired
    private ImpPersonaService impPersonaServ;
    
    @GetMapping
    public String inicio(){
        return "Hola Portfolio";
    }
    
    //Trae todas las personas
    @GetMapping("/list")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> personaList = impPersonaServ.List();
        return new ResponseEntity(personaList, HttpStatus.OK);
    }
    
    //Crea una nueva persona
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DTOPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getName())){
            //MENSAJE lo importa desde security.controller que no se en que video lo muestra??
            return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if(impPersonaServ.existsByName(dtoPersona.getName())){
            return new ResponseEntity("Esa persona ya existe", HttpStatus.BAD_REQUEST);
        }
        
        //Si no hay ningun problema, se crea la persona
        Persona persona = new Persona(dtoPersona.getName(), dtoPersona.getSurname(), dtoPersona.getTitle(), dtoPersona.getCity(), dtoPersona.getImg(), dtoPersona.getEmail(), dtoPersona.getAboutme());
        impPersonaServ.save(persona);
        return new ResponseEntity("Persona agregada exitosamente", HttpStatus.OK);
        
    }
    
    
    //Modificar Persona
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idPersona}")
    public ResponseEntity<?> update(@PathVariable("idPersona") Long idPersona, @RequestBody DTOPersona dtoPersona){
        //Estas validaciones deberian hacerse con todos los campos
        //Verificamos que exista la persona
        if (!impPersonaServ.existsById(idPersona)) {
            return new ResponseEntity("La persona solicitada no existe", HttpStatus.NOT_FOUND);
        }
        //Verifica si el nombre nuevo que se ingreso es de una persona ya existente
        if(impPersonaServ.existsByName(dtoPersona.getName()) && impPersonaServ.getByName(dtoPersona.getName()).get().getIdpersona() != idPersona){
            return new ResponseEntity("Esa persona ya existe", HttpStatus.BAD_REQUEST);
        }
        //EL nombre no puede ser nulo
        if(StringUtils.isBlank(dtoPersona.getName())){
            return new ResponseEntity("El nombre es obligatorio!", HttpStatus.BAD_REQUEST);
        }
        
        //Modificamos la persona
        Persona persona = impPersonaServ.getOne(idPersona).get();
        persona.setName(dtoPersona.getName());
        persona.setSurname(dtoPersona.getSurname());
        persona.setTitle(dtoPersona.getTitle());
        persona.setCity(dtoPersona.getCity());
        persona.setImg(dtoPersona.getImg());
        persona.setEmail(dtoPersona.getEmail());
        
        impPersonaServ.save(persona);
        
        return new ResponseEntity("Persona actualizada exitosamente", HttpStatus.OK);
    }
    
    //Borrar Persona(puede no usarse una vez este implementada la pag)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idPersona}")
    public ResponseEntity<?> delete(@PathVariable("idPersona") Long idPersona){
        //Validamos que el id existe
        if(!impPersonaServ.existsById(idPersona)){
            return new ResponseEntity("La persona solicitada no existe", HttpStatus.NOT_FOUND);
        }
        
        impPersonaServ.delete(idPersona);
        
        return new ResponseEntity("Persona eliminada", HttpStatus.OK);
    }
    
    //Detail
    //No se que hacia este metodo ????
    @GetMapping("/detail/{idPersona}")
    public ResponseEntity<Persona> getById(@PathVariable("idPersona") Long idPersona){
        if(!impPersonaServ.existsById(idPersona)){
            return new ResponseEntity("No existe el ID", HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = impPersonaServ.getOne(idPersona).get();
        
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*
    @GetMapping("/personas/traer/perfil")
    public Optional<Persona> findPersona(){
        return impPersonaServ.getOne((long)1);
    }
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersonasList(){
        return impPersonaServ.List();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        impPersonaServ.save(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{idpersona}")
    public String deletePersona(@PathVariable Long idpersona){
        impPersonaServ.delete(idpersona);
        return "La persona fue borrada exitosamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long idpersona,
                               @RequestParam("name") String newName,
                               @RequestParam("surname") String newSurname,
                               @RequestParam("title") String newTitle,
                               @RequestParam("city") String newCity,
                               @RequestParam("img") String newImg,
                               @RequestParam("email") String newEmail){
        
        Optional<Persona> persona = impPersonaServ.getOne(idpersona);
        
        persona.setName(newName);
        persona.setSurname(newSurname);
        persona.setTitle(newTitle);
        persona.setCity(newCity);
        persona.setImg(newImg);
        persona.setEmail(newEmail);
        
        impPersonaServ.save(persona);
        
        return persona;
    }
    */
}
