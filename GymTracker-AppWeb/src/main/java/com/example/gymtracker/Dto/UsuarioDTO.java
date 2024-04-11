package com.example.gymtracker.Dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private int id;
    private String nombre;
    private String rol;
}
