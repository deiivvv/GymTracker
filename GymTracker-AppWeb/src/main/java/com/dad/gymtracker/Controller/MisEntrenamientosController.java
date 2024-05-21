package com.dad.gymtracker.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import com.dad.gymtracker.Dto.NuevaRutinaDTO;
import com.dad.gymtracker.Service.RutinaService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("mis-entrenamientos")
public class MisEntrenamientosController {

	private final String RUTATEMPLATES= "misEntrenamientos/";
	
	private final RutinaService rutinaService;

	@GetMapping
	public String misEntrenamientos(Model model, HttpSession session) {
		if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
		List<MisEntrenamientosDTO> listaEntrenamientos =rutinaService.buscarEntrenamientosById((int) session.getAttribute("idUsuario"));
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		if(!listaEntrenamientos.isEmpty()) {
			model.addAttribute("listaEntrenamientos", listaEntrenamientos);
		}
		return RUTATEMPLATES + "listar";
	}

	@GetMapping("/mostrar")
	public String mostrarRutina(@RequestParam Integer id,
							   Model model, HttpSession session) {
		if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
		NuevaRutinaDTO nuevaRutinaDTO =rutinaService.buscarRutinaById(id);
		model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
		if(nuevaRutinaDTO!=null) {
			model.addAttribute("nombre", nuevaRutinaDTO.getNombre());
			model.addAttribute("ejercicios", nuevaRutinaDTO.getEjercicios());
			model.addAttribute("series", nuevaRutinaDTO.getSeries());
		}
		return RUTATEMPLATES + "mostrar";
	}
}
