package com.smportfolio.myproject.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "experiencia")
public class Experiencia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExp;
    
    @NotEmpty
    private String nombreExp;
    
    @NotEmpty
    private String descripcionExp;

    //private String imgExp;
    
    //Constructores creo que no hace falta gracias a @Data
    //En caso de que algo falle PONER CONSTRUCTORES
    public Experiencia() {
        
    }
    
    public Experiencia(String nombreExp, String descripcionExp){
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
    }
    
}
