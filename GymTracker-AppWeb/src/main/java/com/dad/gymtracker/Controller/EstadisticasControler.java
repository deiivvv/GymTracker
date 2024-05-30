package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dad.gymtracker.Service.EstadisticasService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/estadisticas")
public class EstadisticasControler {
	
	private final String RUTATEMPLATES= "estadisticas/";
	private final EstadisticasService estadisticasService; 

	@GetMapping
    public String estadisticas(Model model, HttpSession session){
        model.addAttribute("idUsuario", session.getAttribute("idUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return RUTATEMPLATES + "listar";
    }
	
	@GetMapping("/contar")
	@ResponseBody
    public Long contarEjercicios(@RequestParam String ids, HttpSession session) {
		return estadisticasService.contarEjerciciosGrupoMuscular((Integer) session.getAttribute("idUsuario"), ids); 
    }
}
