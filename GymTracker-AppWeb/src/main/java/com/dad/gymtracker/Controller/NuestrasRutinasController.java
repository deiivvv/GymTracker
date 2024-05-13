package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import com.dad.gymtracker.Service.RutinaService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class NuestrasRutinasController {

	private final String RUTATEMPLATES= "nuestrasRutinas/";
	
	private final RutinaService rutinaService;

	@GetMapping("/nuestras-rutinas")
	public String nuestrasRutinas(Model model, HttpSession session) {
		if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
		List<MisEntrenamientosDTO> listaEntrenamientos =rutinaService.buscarEntrenamientosById(-1);
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		if(!listaEntrenamientos.isEmpty()) {
			model.addAttribute("listaEntrenamientos", listaEntrenamientos);
		}
		return RUTATEMPLATES + "listar";
	}
}
