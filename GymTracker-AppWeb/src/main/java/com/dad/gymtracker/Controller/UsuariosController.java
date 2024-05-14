package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.UsuarioPerfilDTO;
import com.dad.gymtracker.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuariosController {

    private final String RUTATEMPLATES= "usuarios/";
    private final UsuarioService usuarioService;

    @GetMapping("/crear")
    public String crearUsuario(Model model, HttpSession session){
        model.addAttribute("usuarioPerfilDTO", new UsuarioPerfilDTO());
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return RUTATEMPLATES + "crear";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute UsuarioPerfilDTO usuarioPerfilDTO){
        usuarioService.crearUsuario(usuarioPerfilDTO.getUsuarioDTO(), usuarioPerfilDTO.getPerfilDTO());
        return "redirect:/";
    }

    @GetMapping("/comprobar")
    @ResponseBody
    public boolean comprobarUsuario(@RequestParam String nombre){
        if(usuarioService.buscarUsuarioByNombre(nombre)==null){
            return false;
        };
        return true;
    }

}


