package com.smportfolio.myproject.security.entity;

import com.smportfolio.myproject.security.enums.RolNombre;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rol_id;
    
    @NotEmpty
    @Enumerated(EnumType.STRING)//En la columna de la bdd solo se podran poner valores alfanumericos
    private RolNombre rolNombre;//Definimos los valores a poner en esta columna
    
    //Constructor
    
    public Rol(){
        
    }
    
    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    //Getter n Setter
    public Long getIdRol() {
        return rol_id;
    }

    public void setIdRol(Long rol_id) {
        this.rol_id = rol_id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    
}
