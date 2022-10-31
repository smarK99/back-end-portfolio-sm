package com.smportfolio.myproject.dto;

import javax.validation.constraints.NotBlank;

public class DTOPersona {
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String surname;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String imgProfile;
    
    @NotBlank
    private String imgBanner;
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String aboutme;
    

    public DTOPersona() {
    
    }
    
    public DTOPersona(String name, String surname, String title, String city, String imgProfile, String imgBanner, String email, String aboutme) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.city = city;
        this.imgProfile = imgProfile;
        this.imgBanner = imgBanner;
        this.email = email;
        this.aboutme = aboutme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    
}
