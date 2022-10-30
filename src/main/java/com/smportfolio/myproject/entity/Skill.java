package com.smportfolio.myproject.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "skill")
public class Skill implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    
    @NotEmpty
    private String nombreSkill;
    
    @NotNull
    private double porcentaje;
    
    //private String imgSkill;
    
    public Skill(){
        
    }
    
    public Skill(String nombreSkill, double porcentaje) {
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
    }
    
    
}
