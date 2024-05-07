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
    		model.addAttribute("mensajeErrorWarning", "Usuario o contraseña incorrectos");
    		model.addAttribute("usuario", new UsuarioDTO());
            return "/login/inicioSesion";
    	}else if(usuarioDTOBD.getRol().equals("bloqueado")){
            model.addAttribute("mensajeErrorDanger", "Usuario bloqueado");
            model.addAttribute("usuario", new UsuarioDTO());
            return "/login/inicioSesion";
        }
        session.setAttribute("idUsuario", usuarioDTOBD.getId());
        session.setAttribute("rolUsuario", usuarioDTOBD.getRol());
        return "redirect:/menu";

    }

    @GetMapping("/menu")
    public String menu(Model model, HttpSession session){
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return "menu";
    }

    @GetMapping("/cerrar-sesion")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
