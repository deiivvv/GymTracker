package com.dad.gymtracker.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Immutable
public class PerfilUsuarioDTO {

    @Id
    private int id;
    private String nombre;
    private int edad;
    private float altura;
    private float peso;
    private String genero;

}
