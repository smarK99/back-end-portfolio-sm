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
@Table(name = "proyecto")
public class Proyecto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;
    
    @NotEmpty
    private String nombreProyecto;
    
    @NotEmpty
    private String descripcionProy;

    @NotEmpty
    private String urlProy;
    
    //private String img;
    
    public Proyecto(){
        
    }
    
    public Proyecto(String nombreProyecto, String descripcionProy , String urlProy) {
        this.nombreProyecto = nombreProyecto;
        this.descripcionProy = descripcionProy;
        this.urlProy = urlProy;
    }
    
    
}
