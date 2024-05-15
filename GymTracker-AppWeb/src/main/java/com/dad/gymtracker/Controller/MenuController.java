package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("permitAll()")
    public String inicioSesion(@ModelAttribute UsuarioDTO usuario,
                       HttpSession session, Model model){
    	UsuarioDTO usuarioDTOBD=usuarioService.buscarUsuarioByNombreAndContrasena(usuario.getNombre(), usuario.getContrasena());
    	if(usuarioDTOBD==null) {
    		model.addAttribute("mensajeErrorWarning", "Usuario o contraseña incorrectos");
    		model.addAttribute("usuario", new UsuarioDTO());
            return "login/inicioSesion";
    	}else if(usuarioDTOBD.getRol().equals("bloqueado")){
            model.addAttribute("mensajeErrorDanger", "Usuario bloqueado");
            model.addAttribute("usuario", new UsuarioDTO());
            return "login/inicioSesion";
        }
        session.setAttribute("idUsuario", usuarioDTOBD.getId());
        session.setAttribute("rolUsuario", usuarioDTOBD.getRol());
        return "redirect:/menu";

    }

    @GetMapping("/menu")
//    @PreAuthorize("hasAuthority('READ')")
    public String menu(Model model, HttpSession session){
//        if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return "menu";
    }

    @GetMapping("/cerrar-sesion")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
