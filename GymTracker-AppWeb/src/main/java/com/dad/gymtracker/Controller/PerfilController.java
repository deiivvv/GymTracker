package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.PerfilUsuarioDTO;
import com.dad.gymtracker.Service.PerfilService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PerfilController {
	
	private final String RUTATEMPLATES= "/perfil";
    private PerfilService perfilService;

    @GetMapping("/perfil")
    public String mostrar(Model model, HttpSession session){
        model.addAttribute("perfilUsuario", perfilService.obtenerUsuariosConPerfil((int)session.getAttribute("idUsuario")));
        return RUTATEMPLATES + "/mostrar";
    }
    
    @GetMapping("/perfil/editar")
    public String editar(Model model, HttpSession session){
        model.addAttribute("perfilUsuarioDTO", perfilService.obtenerUsuariosConPerfil((int)session.getAttribute("idUsuario")));
        return RUTATEMPLATES + "/editar";
    }
    
    @PostMapping("/perfil/guardar")
    public String editar(@ModelAttribute PerfilUsuarioDTO perfilUsuarioDTO,
    					HttpSession session){
    	perfilUsuarioDTO.setId((int)session.getAttribute("idUsuario"));
        perfilService.editarUsuario(perfilUsuarioDTO);
        return "redirect:/perfil";
    }
    
    @GetMapping("/perfil/elimiar")
    public String eliminar(HttpSession session){
    	perfilService.borrarUsuario((int) session.getAttribute("idUsuario"));
        return "redirect:/cerrar-sesion";
    }

}
