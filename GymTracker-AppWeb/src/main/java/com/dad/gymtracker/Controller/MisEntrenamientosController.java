package com.dad.gymtracker.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import com.dad.gymtracker.Service.MisEntrenamientosService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MisEntrenamientosController {

	private final String RUTATEMPLATES= "/misEntrenamientos/";
	
	private final MisEntrenamientosService misEntrenamientosService;

	@GetMapping("/mis-entrenamientos")
	public String misEntrenamientos(Model model, HttpSession session) {
		List<MisEntrenamientosDTO> listaEntrenamientos =misEntrenamientosService.buscarEntrenamientosById((int) session.getAttribute("idUsuario"));
		if(!listaEntrenamientos.isEmpty()) {
			model.addAttribute("listaEntrenamientos", listaEntrenamientos);
		}
		return RUTATEMPLATES + "listar";
	}
}
