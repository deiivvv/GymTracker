package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.gymtracker.Dto.UsuarioDTO;


@Controller
public class LoginController {
	
	private final String RUTATEMPLAES="login/";
	
	@GetMapping("/")
    public String inicioSesion(Model model){
		model.addAttribute("usuario", new UsuarioDTO());
        return RUTATEMPLAES + "inicioSesion";
    }
}
