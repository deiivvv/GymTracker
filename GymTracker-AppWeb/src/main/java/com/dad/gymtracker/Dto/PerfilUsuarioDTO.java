package com.dad.gymtracker.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PerfilUsuarioDTO {

    @Id
    private Long id;
    private String nombre;
    private int edad;
    private float altura;
    /*private float peso;
    private String mail;
    private String genero;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

}
