package com.dad.gymtracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioPerfilDTO {

	private UsuarioDTO usuarioDTO;
	private PerfilDTO perfilDTO;
}
