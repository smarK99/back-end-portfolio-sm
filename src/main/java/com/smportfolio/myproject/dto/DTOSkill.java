package com.smportfolio.myproject.dto;

import javax.validation.constraints.NotBlank;

public class DTOSkill {
    
    @NotBlank
    private String nombreSkill;
    
    @NotBlank
    private double porcentaje;
    
    public DTOSkill(){
        
    }
    
    public DTOSkill(String nombreSkill, double porcentaje) {
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
    }

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
