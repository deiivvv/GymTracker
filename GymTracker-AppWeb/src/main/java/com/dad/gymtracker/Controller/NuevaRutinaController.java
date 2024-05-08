package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpSession;
import com.dad.gymtracker.Dto.NuevaRutinaDTO;
import com.dad.gymtracker.Service.RutinaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class NuevaRutinaController {

	private final RutinaService rutinaService;
	private final String RUTATEMPLATES = "/nuevaRutina/";

	@GetMapping("/nueva-rutina")
	public String crear(Model model, HttpSession session) {
		if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
		model.addAttribute("nuevaRutinaForm",new NuevaRutinaDTO());
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		return RUTATEMPLATES + "crear";
	}

	@PostMapping("/nueva-rutina")
	public String crear(@ModelAttribute NuevaRutinaDTO nuevaRutinaDTO, HttpSession session) {
		Integer idUsuario = (Integer) session.getAttribute("idUsuario");
		rutinaService.crearRutina(nuevaRutinaDTO,idUsuario);
		return "redirect:/mis-entrenamientos";
	}
	
	@GetMapping("/mis-entrenamientos/listar")
	public String listarRutina(@RequestParam Integer id,
										Model model, HttpSession session) {
		if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
		NuevaRutinaDTO nuevaRutinaDTO =rutinaService.buscarRutinaById(id);
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		if(nuevaRutinaDTO!=null) {
			model.addAttribute("nombre", nuevaRutinaDTO.getNombre());
			model.addAttribute("ejercicios", nuevaRutinaDTO.getEjercicios());
			model.addAttribute("series", nuevaRutinaDTO.getSeries());
		}
		return RUTATEMPLATES + "listar";
	}
}
