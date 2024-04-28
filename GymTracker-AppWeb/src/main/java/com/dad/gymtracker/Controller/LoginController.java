package com.dad.gymtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.gymtracker.Dto.UsuarioDTO;


@Controller
public class LoginController {
	
	private final String RUTATEMPLAES="/login/";
	
	@GetMapping("/")
    public String inicioSesion(Model model){
		model.addAttribute("usuario", new UsuarioDTO());
        return RUTATEMPLAES + "inicioSesion";
    }
	
	@GetMapping("cerrarSesion")
	public String CerrarSesion() {
		return RUTATEMPLAES +  "cerrarSesion";
	}
	
	@GetMapping("CrearUsuario")
	public String CrearUsuario() {
		return RUTATEMPLAES + "crear";
	}
	
	@GetMapping("ModificarUsuario")
	public String ModificarUsuario() {
		return RUTATEMPLAES + "modificar";
	}
	
	@GetMapping("EliminarUsuario")
	public String EliminarUsuario() {
		return RUTATEMPLAES + "eliminar";
	}
	
	
}
