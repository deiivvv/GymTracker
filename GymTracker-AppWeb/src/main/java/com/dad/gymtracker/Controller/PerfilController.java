package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.PerfilService;
import com.dad.gymtracker.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/perfil")
public class PerfilController {
	
	private final String RUTATEMPLATES= "perfil/";
    private PerfilService perfilService;
    private UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrar(Model model, HttpSession session){
        if(session.getAttribute("idUsuario")==null) return "redirect:/";
        model.addAttribute("perfilUsuario", perfilService.obtenerUsuariosConPerfil((int)session.getAttribute("idUsuario")));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return RUTATEMPLATES + "mostrar";
    }


    @GetMapping("/cambiar-contrasena")
    @ResponseBody
    public boolean cambiarContrasena(@RequestParam String contrasena, HttpSession session){
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioById((int)session.getAttribute("idUsuario"));
        return passwordEncoder.matches(contrasena, usuarioDTO.getContrasena());
    }


    @GetMapping("/cambiando-contrasena")
    public String cambioContrasena(@RequestParam String contrasena, HttpSession session){
        usuarioService.cambiarContrasena((int)session.getAttribute("idUsuario"), contrasena);
        return "redirect:/perfil";
    }

    @GetMapping("/editar")
    public String editar(Model model, HttpSession session){
        if(session.getAttribute("idUsuario")==null) return "redirect:/";
        model.addAttribute("perfilUsuarioDTO", perfilService.obtenerUsuariosConPerfil((int)session.getAttribute("idUsuario")));
        return RUTATEMPLATES + "editar";
    }
    
    @PostMapping("/guardar")
    public String editar(@ModelAttribute PerfilDTO perfilDTO,
    					HttpSession session){
    	perfilDTO.setId((int)session.getAttribute("idUsuario"));
        perfilService.editarUsuario(perfilDTO);
        return "redirect:/perfil";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(HttpSession session){
        if(session.getAttribute("idUsuario")==null) return "redirect:/cerrar-sesion";
    	perfilService.borrarUsuario((int) session.getAttribute("idUsuario"));
        return "redirect:/cerrar-sesion";
    }
}
