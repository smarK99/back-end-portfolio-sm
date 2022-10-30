package com.smportfolio.myproject.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    @Column(unique = true)//Para que no haya usernames iguales
    private String username;
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    @Email
    private String email;
    
    //Un usuario puede tener muchos roles y un rol pueden tenerlo varios usuarios
    //Vamos a crear la tabla intermedia aqui, calculo que es lo mismo que crearla manualmente como otra clase dentro de Entity
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> rolesList = new HashSet<>();//Set collection no puede tener elementos duplicados
    
    //Constructores
    public Usuario(){
    }

    public Usuario(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    //Setters n Getters

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRolesList() {
        return rolesList;
    }

    public void setRolesList(Set<Rol> rolesList) {
        this.rolesList = rolesList;
    }
    
}
