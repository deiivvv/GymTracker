package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final String RUTATEMPLAES = "/admin/";
    private final UsuarioService usuarioService;

    @GetMapping("/admin")
    public String administrarUsuarios(Model model, HttpSession session) {
        List<UsuarioDTO> listaUsuarios =usuarioService.buscarAllUsuarios();
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        if(!listaUsuarios.isEmpty()) {
            model.addAttribute("listaUsuarios", listaUsuarios);
        }
        return RUTATEMPLAES + "listar";
    }

    @PostMapping("/admin/cambiar-rol")
    public String cambiarRol(@RequestParam Integer id,
                             @RequestParam String rol,
                             @RequestParam String accion,
                             HttpSession session){
        if(accion.equals("cambiarRol")){
            usuarioService.cambiarRol(id, rol);
            if(session.getAttribute("idUsuario") == id){
                session.setAttribute("rolUsuario", rol);
                if(session.getAttribute("rolUsuario")!="administrador"){
                    return "redirect:/menu";
                }
            }
            return "redirect:/admin";
        }else if(accion.equals("acceso")){
            session.setAttribute("idUsuario", id);
            return "redirect:/menu";
        }
        return null;
    }
}