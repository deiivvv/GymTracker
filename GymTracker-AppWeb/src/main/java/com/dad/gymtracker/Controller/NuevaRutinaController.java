package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class NuevaRutinaController {
	
	private final String RUTATEMPLATES = "/nuevaRutina/";
	
	@GetMapping("/nueva-rutina")
	public String crear() {
		return RUTATEMPLATES + "crear";
	}

}
