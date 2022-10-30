package com.smportfolio.myproject.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;



@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpersona;
    
    @NotEmpty
    @Size(min = 1, max = 20, message = "Error")
    private String name;
    
    @NotEmpty
    @Size(min = 1, max = 30, message = "Error")
    private String surname;
    
    @NotEmpty
    @Size(min = 1, max = 40, message = "Error")
    private String title;
    
    @NotEmpty
    @Size(min = 1, max = 20, message = "Error")
    private String city;
    
    private String img;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String aboutme;
    
    //Constructores
    
    public Persona(){
        
    }
    
    public Persona(String name, String surname, String title, String city, String img, String email, String aboutme) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.city = city;
        this.img = img;
        this.email = email;
        this.aboutme = aboutme;
    }
    
}
