package com.smportfolio.myproject.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "educacion")
public class Educacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEducacion;
    
    @NotEmpty
    private String nombreEdu;
    
    @NotEmpty
    private String descripcionEdu;
    
    private String imgEdu;
    
    //Constructores
    public Educacion(){
        
    }
    
    public Educacion(String nombreEdu, String descripcionEdu, String imgEdu){
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.imgEdu = imgEdu;
    }
    
    public Educacion(String nombreEdu, String descripcionEdu){
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
    }
}
