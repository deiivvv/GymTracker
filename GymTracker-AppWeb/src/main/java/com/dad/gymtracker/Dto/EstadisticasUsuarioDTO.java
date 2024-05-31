package com.dad.gymtracker.Dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadisticasUsuarioDTO {
    @Id
    private int id;
    private String nombre;
    private long entrenos;
    private long ejercicios;
    private Double volumen;
    private long series;
}
