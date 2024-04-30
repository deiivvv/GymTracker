package com.dad.gymtracker.Dto;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
	@Id
    private int id;
    private String nombre;
    private String contrasena;
    private String rol;
}
