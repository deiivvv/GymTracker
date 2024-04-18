package com.dad.gymtracker.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dad.gymtracker.Dto.Usuario;
import com.dad.gymtracker.Respository.UsuarioRepository;

@Controller/*
@AllArgsConstructor 
@NoArgsConstructor*/
public class pruebaBBDD_Controller {
	
    private UsuarioRepository usuarioRepository;
/*
    @GetMapping("/usuarios")
    @ResponseBody
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }*/

}
