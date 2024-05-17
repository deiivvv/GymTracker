package com.dad.gymtracker.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.gymtracker.Dto.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequestMapping("/")
public class LoginController {
    //    @PreAuthorize("permitAll()")
	private final String RUTATEMPLAES="login/";
    @GetMapping("/")
    public String inicioSesion(Model model){
		model.addAttribute("usuarioDTO", new UsuarioDTO());
        return RUTATEMPLAES + "inicioSesion";
    }


}
