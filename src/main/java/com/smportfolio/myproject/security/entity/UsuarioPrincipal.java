package com.smportfolio.myproject.security.entity;



import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Tiene toda la seguridad
public class UsuarioPrincipal implements UserDetails{
    
    private String name;
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    
    //Constructor
    public UsuarioPrincipal(){
        
    }

    public UsuarioPrincipal(String name, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }
    
    //Metodo para construir usuario con roles y +
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRolesList().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        
        return new UsuarioPrincipal(usuario.getName(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), authorities);
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    //Metodos implementados
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
