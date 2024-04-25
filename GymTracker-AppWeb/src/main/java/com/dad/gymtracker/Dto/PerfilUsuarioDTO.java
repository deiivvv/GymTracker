package com.dad.gymtracker.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PerfilUsuarioDTO {

    @Id
    private int id;
    private String nombre;
    private int edad;
    private float altura;
    private float peso;
    private String genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setPeso(float peso) {
        this.peso = peso;
    }
    public float getPeso() {
        return peso;
    }

    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
