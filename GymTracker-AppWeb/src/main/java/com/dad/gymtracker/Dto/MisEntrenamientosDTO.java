package com.dad.gymtracker.Dto;

import org.hibernate.annotations.Immutable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MisEntrenamientosDTO {

    @Id
    private int id;
    private String nombre;
    private String fecha;

}