package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;

@Controller
@AllArgsConstructor
public class MenuController {

	private final UsuarioService usuarioService;
	
    @RequestMapping("/inicio")
    public String inicioSesion(@ModelAttribute UsuarioDTO usuario,
                       HttpSession session, Model model){

    	UsuarioDTO usuarioDTOBD=usuarioService.buscarUsuario(usuario.getNombre(), usuario.getContrasena());
    	if(usuarioDTOBD==null) {
    		model.addAttribute("mensajeError", "El usuario no existe");
    		model.addAttribute("usuario", new UsuarioDTO());
            return "/login/inicioSesion";
    	}
        session.setAttribute("idUsuario", usuarioDTOBD.getId());
        return "redirect:/menu";

    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

   
    @GetMapping("/ejercicios")
    public String ejercicios(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "/ejercicios/listar";
    }

    @GetMapping("/cerrar-sesion")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
