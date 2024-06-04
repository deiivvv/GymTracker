package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;

@Controller
@AllArgsConstructor
public class MenuController {

	private final UsuarioService usuarioService;

    @RequestMapping("/menu")
    @PreAuthorize("permitAll()")
    public String menu(@RequestParam(required = false) String admin,
                        Model model, HttpSession session){
        if(admin == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsuarioDTO usuarioDTO= usuarioService.buscarUsuarioByNombre(authentication.getName());
            session.setAttribute("idUsuario", usuarioDTO.getId());
            session.setAttribute("rolUsuario", usuarioDTO.getRol());
            if(usuarioDTO.getRol().equals("bloqueado")){
                return "redirect:/?error=bloqueado";
            }
        }
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return "menu";
    }

    @GetMapping("/cerrar-sesion")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
