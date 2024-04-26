package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.PerfilUsuarioDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UsuariosController {

    private final String RUTATEMPLATES= "/usuarios";
    private final UsuarioService usuarioService;

    @GetMapping("/usuario/crear")
    public String crearUsuario(Model model){
        model.addAttribute("usuarioDTO" ,new UsuarioDTO());
        model.addAttribute("perfilUsuarioDTO" ,new PerfilUsuarioDTO());
        return RUTATEMPLATES + "/crear";
    }

    @PostMapping("/usuario/crear")
    public String crearUsuario(@ModelAttribute UsuarioDTO usuarioDTO,
                               @ModelAttribute PerfilUsuarioDTO perfilUsuarioDTO){
        usuarioService.crearUsuario(usuarioDTO, perfilUsuarioDTO);
        return "redirect:/";
    }

}


