package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.NuevaRutinaDTO;
import com.dad.gymtracker.Service.RutinaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class NuevaRutinaController {

	private final RutinaService rutinaService;
	private final String RUTATEMPLATES = "/nuevaRutina/";

	@GetMapping("/nueva-rutina")
	public String mostrar(Model model) {
		model.addAttribute("nuevaRutinaForm",new NuevaRutinaDTO());
		return RUTATEMPLATES + "crear";
	}

	@PostMapping("/nueva-rutina")
	public String crear(@ModelAttribute NuevaRutinaDTO nuevaRutinaDTO, HttpSession session) {
		Integer idUsuario = (Integer) session.getAttribute("idUsuario");
		rutinaService.crearRutina(nuevaRutinaDTO,idUsuario);
		return "redirect:/nueva-rutina";
	}
}
