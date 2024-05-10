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
public class UsuariosController {

    private final String RUTATEMPLATES= "/usuarios/";
    private final UsuarioService usuarioService;

    @GetMapping("/usuario/crear")
    public String crearUsuario(Model model, HttpSession session){
        model.addAttribute("usuarioPerfilDTO", new UsuarioPerfilDTO());
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        return RUTATEMPLATES + "crear";
    }

    @PostMapping("/usuario/crear")
    public String crearUsuario(@ModelAttribute UsuarioPerfilDTO usuarioPerfilDTO){
        usuarioPerfilDTO.getUsuarioDTO().setContrasena(usuarioService.hashContrasena(usuarioPerfilDTO.getUsuarioDTO().getContrasena()));
        usuarioService.crearUsuario(usuarioPerfilDTO.getUsuarioDTO(), usuarioPerfilDTO.getPerfilDTO());
        return "redirect:/";
    }

    @GetMapping("/usuario/comprobar")
    @ResponseBody
    public boolean comprobarUsuario(@RequestParam String nombre){
        return !(usuarioService.buscarUsuarioByNombre(nombre)==null);
    }
}


