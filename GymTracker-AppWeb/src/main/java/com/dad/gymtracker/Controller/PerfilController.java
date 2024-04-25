package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Service.PerfilService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PerfilController {
    private PerfilService perfilService;

    public PerfilController(PerfilService perfilService){
        this.perfilService=perfilService;
    }

    @GetMapping("/perfil")
    /*@PathVariable Integer id,*/
    public String perfil(Model model, HttpSession session){
        model.addAttribute("perfilUsuario", perfilService.obtenerUsuariosConPerfil(1));
        return "perfil";
    }

}
