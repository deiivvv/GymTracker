package com.example.pruebasspringsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pruebasController {

	
	@GetMapping("/")
	public String login(Model model){
		model.addAttribute("usuario", new UsuarioDTO());
        return "hola";
    }
	
	
    @GetMapping("/menu")
    public String a(){
        return "hola";
    }
}
