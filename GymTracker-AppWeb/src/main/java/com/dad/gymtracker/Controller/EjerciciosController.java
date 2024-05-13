package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EjerciciosController {

	private final String RUTATEMPLATES= "ejercicios/";
	
    @GetMapping("/ejercicios")
    public String ejercicios(Model model, HttpSession session){
        if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return RUTATEMPLATES + "listar";
    }
}
