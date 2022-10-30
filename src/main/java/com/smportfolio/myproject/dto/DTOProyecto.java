package com.smportfolio.myproject.dto;

import javax.validation.constraints.NotBlank;

public class DTOProyecto {
    
    @NotBlank
    private String nombreProyecto;
    
    @NotBlank
    private String descripcionProy;

    @NotBlank
    private String urlProy;
    
    public DTOProyecto(){
        
    }
    
    public DTOProyecto(String nombreProyecto, String descripcionProy, String urlProy) {
        this.nombreProyecto = nombreProyecto;
        this.descripcionProy = descripcionProy;
        this.urlProy = urlProy;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProy() {
        return descripcionProy;
    }

    public void setDescripcionProy(String descripcionProy) {
        this.descripcionProy = descripcionProy;
    }

    public String getUrlProy() {
        return urlProy;
    }

    public void setUrlProy(String urlProy) {
        this.urlProy = urlProy;
    }
    
    
}
