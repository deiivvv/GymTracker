package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class NuevaRutinaController {
	
	private final String RUTATEMPLATES = "/nuevaRutina/";
	
	@GetMapping("/nueva-rutina")
	public String crear(Model model, HttpSession session) {
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		return RUTATEMPLATES + "crear";
	}

}
