package com.dad.gymtracker.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import com.dad.gymtracker.Service.RutinaService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MisEntrenamientosController {

	private final String RUTATEMPLATES= "/misEntrenamientos/";
	
	private final RutinaService rutinaService;

	@GetMapping("/mis-entrenamientos")
	public String misEntrenamientos(Model model, HttpSession session) {
		List<MisEntrenamientosDTO> listaEntrenamientos = rutinaService.buscarEntrenamientosById((int) session.getAttribute("idUsuario"));
		if(!listaEntrenamientos.isEmpty()) {
			model.addAttribute("listaEntrenamientos", listaEntrenamientos);
		}
		return RUTATEMPLATES + "listar";
	}
}
