package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import com.dad.gymtracker.Service.CalendarioService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CalendarioController {

private final String RUTATEMPLATES= "calendario/";
private final CalendarioService calendarioService; 
	
    @GetMapping("/calendario")
    public String calendario(Model model, HttpSession session){
        if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
        model.addAttribute("idUsuario", session.getAttribute("idUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));

        MisEntrenamientosDTO fechas= calendarioService.buscarFechasById((int) session.getAttribute("idUsuario"));
        if(fechas!=null){
            model.addAttribute("fechas", fechas.getFecha());
        }
        return RUTATEMPLATES + "listar";
    }
	
}
