package com.smportfolio.myproject.dto;

import javax.validation.constraints.NotBlank;

public class DTOEducacion {
    
    @NotBlank
    private String nombreEdu;
    
    @NotBlank
    private String descripcionEdu;
    
    private String imgEdu;
    
    //Constructores
    public DTOEducacion(){
        
    }
    
    public DTOEducacion(String nombreEdu, String descripcionEdu, String imgEdu){
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.imgEdu = imgEdu;
    }
    
    public DTOEducacion(String nombreEdu, String descripcionEdu){
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
    }
    
    //S y G
    public String getNombreEdu() {
        return nombreEdu;
    }

    public void setNombreEdu(String nombreEdu) {
        this.nombreEdu = nombreEdu;
    }

    public String getDescripcionEdu() {
        return descripcionEdu;
    }

    public void setDescripcionEdu(String descripcionEdu) {
        this.descripcionEdu = descripcionEdu;
    }

    public String getImgEdu() {
        return imgEdu;
    }

    public void setImgEdu(String imgEdu) {
        this.imgEdu = imgEdu;
    }
    
}
