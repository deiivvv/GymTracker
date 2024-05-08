package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Dto.UsuarioPerfilDTO;
import com.dad.gymtracker.Service.PerfilService;
import com.dad.gymtracker.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final String RUTATEMPLAES = "/admin/";
    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    @GetMapping("/admin")
    public String administrarUsuarios(Model model, HttpSession session) {
        if(session.getAttribute("idUsuario")==null || !(session.getAttribute("rolUsuario").equals("administrador"))) return "redirect:/cerrar-sesion";

        List<UsuarioDTO> listaUsuarios =usuarioService.buscarAllUsuarios();
        model.addAttribute("idUsuario", session.getAttribute("idUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        model.addAttribute("newUsuario", new UsuarioDTO());
        if(!listaUsuarios.isEmpty()) {
            model.addAttribute("listaUsuarios", listaUsuarios);
        }
        return RUTATEMPLAES + "listar";
    }

    @PostMapping("/admin/accion")
    public String accion(@RequestParam Integer id,
                             @RequestParam String rol,
                             @RequestParam String accion){
        return switch (accion) {
            case "cambiarRol" -> "redirect:/admin/cambiar-rol?id=" + id + "&rol=" + rol;
            case "acceso" -> "redirect:/admin/acceso?id=" + id+"&rol="+rol;
            case "eliminar" -> "redirect:/admin/eliminar?id=" + id;
            default -> null;
        };
    }
    
    @GetMapping("/admin/cambiar-rol")
    public String cambiarRol(@RequestParam Integer id,
    						 @RequestParam String rol, HttpSession session) {
    	 usuarioService.cambiarRol(id, rol);
         if(session.getAttribute("idUsuario") == id){
             session.setAttribute("rolUsuario", rol);
             if(rol.equals("usuario")){
                 return "redirect:/menu";
             }else if(rol.equals("bloqueado")){
                 return "redirect:/";
             }
         }
         return "redirect:/admin";
    }
    
    @GetMapping("/admin/acceso")
    public String acceso(@RequestParam Integer id,
                         @RequestParam String rol, HttpSession session){
        session.setAttribute("rolUsuario", rol);
    	session.setAttribute("idUsuario", id);
    	return "redirect:/menu";
    }
    
    @GetMapping("/admin/eliminar")
    public String eliminar(@RequestParam Integer id, HttpSession session){
    	perfilService.borrarUsuario(id);
    	if(session.getAttribute("idUsuario") == id){
    		return "redirect:/cerrar-sesion";
    	}
    	return "redirect:/admin";
        
    }
    
    @PostMapping("/admin/crear-usuario")
    public String crearUsuario(@ModelAttribute UsuarioDTO newUsuario) {
    	PerfilDTO perfilDto= PerfilDTO.builder()
    								.nombre(newUsuario.getNombre())
    								.edad(0)
    								.altura(0)
    								.peso(0)
    								.genero("Sin especificar").build();
    	usuarioService.crearUsuario(newUsuario, perfilDto);
    	return "redirect:/admin";
    }
}