package com.dad.gymtracker.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NuevaRutinaDTO {
    private String nombre;
    //formato para ejercicios id@nombre,id@nombre....
    private String ejercicios;

}
