package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.gymtracker.Dto.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
	private final String RUTATEMPLAES="login/";
    @GetMapping("/")
    public String inicioSesion(@RequestParam(required = false, defaultValue = "") String error,
                               Model model){
		model.addAttribute("usuarioDTO", new UsuarioDTO());
        switch (error){
            case "true": model.addAttribute("mensajeErrorWarning", "Usuario o contraseña incorrectos"); break;
            case "bloqueado": model.addAttribute("mensajeErrorDanger", "Usuario bloqueado"); break;
        }

        return RUTATEMPLAES + "inicioSesion";
    }


}
