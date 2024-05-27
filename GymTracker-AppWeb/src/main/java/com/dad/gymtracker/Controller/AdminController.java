package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Dto.UsuarioPerfilDTO;
import com.dad.gymtracker.Service.PerfilService;
import com.dad.gymtracker.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('CREATE')")
public class AdminController {

    private final String RUTATEMPLAES = "admin/";
    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    @GetMapping
    public String administrarUsuarios(@RequestParam(defaultValue = "%") String Srol,
    								  @RequestParam(defaultValue = "1") Integer pag,
    								  Model model, HttpSession session) {
        if(session.getAttribute("idUsuario")==null || !(session.getAttribute("rolUsuario").equals("administrador"))) return "redirect:/cerrar-sesion";
        model.addAttribute("Srol", Srol);
        if(Srol.equals("todos")) {Srol="%";};
        List<UsuarioDTO> listaUsuarios =usuarioService.buscarUsuariosByRol(Srol);
        model.addAttribute("idUsuario", session.getAttribute("idUsuario"));
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        model.addAttribute("pag", pag);
        model.addAttribute("newUsuario", new UsuarioDTO());
        if(!listaUsuarios.isEmpty()) {
            model.addAttribute("listaUsuarios", listaUsuarios);
        }
        return RUTATEMPLAES + "listar";
    }

    @PostMapping("/accion")
    public String accion(@RequestParam Integer id,
                         @RequestParam String rol,
                         @RequestParam String contrasena,
                         @RequestParam String accion,
                         @RequestParam String Srol,
                         @RequestParam Integer pag){
    	if(Srol.equals("%")){Srol="todos";}
        return switch (accion) {
            case "cambiarRol" -> "redirect:/admin/cambiar-rol?id=" + id + "&rol=" + rol + "&Srol=" + Srol + "&pag=" + pag;
            case "acceso" -> "redirect:/admin/acceso?id=" + id+"&rol="+rol + "&Srol=" + Srol + "&pag=" + pag;
            case "eliminar" -> "redirect:/admin/eliminar?id=" + id + "&Srol=" + Srol + "&pag=" + pag;
            case "cambiarContrasena" -> "redirect:/admin/cambiar-contrasena?id=" + id + "&contrasena=" + contrasena + "&Srol=" + Srol + "&pag=" + pag; 
            default -> null;
        };
    }

    @GetMapping("/cambiar-contrasena")
    public String cambiarContrasena(@RequestParam Integer id,
                                    @RequestParam String contrasena,
                                    @RequestParam String Srol,
                                    @RequestParam Integer pag) {
        usuarioService.cambiarContrasena(id, contrasena);
        return "redirect:/admin?Srol=" + Srol + "&pag=" + pag;
    }

    @GetMapping("/cambiar-rol")
    public String cambiarRol(@RequestParam Integer id,
    						 @RequestParam String rol,
    						 @RequestParam String Srol,
                             @RequestParam Integer pag, HttpSession session) {
    	 usuarioService.cambiarRol(id, rol);
         if(session.getAttribute("idUsuario") == id){
             session.setAttribute("rolUsuario", rol);
             if(rol.equals("usuario")){
                 return "redirect:/menu";
             }else if(rol.equals("bloqueado")){
                 return "redirect:/";
             }
         }
         return "redirect:/admin?Srol=" + Srol + "&pag=" + pag;
    }
    
    @GetMapping("/acceso")
    public String acceso(@RequestParam Integer id,
                         @RequestParam String rol,
                         @RequestParam String Srol,
                         @RequestParam Integer pag, HttpSession session){
        session.setAttribute("rolUsuario", rol);
    	session.setAttribute("idUsuario", id);
    	return "redirect:/menu?admin=true";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, 
    					   @RequestParam String Srol,
    					   @RequestParam Integer pag, HttpSession session){
    	perfilService.borrarUsuario(id);
    	if(session.getAttribute("idUsuario") == id){
    		return "redirect:/cerrar-sesion";
    	}
    	return "redirect:/admin?Srol=" + Srol + "&pag=" + pag;
        
    }
    
    @PostMapping("/crear-usuario")
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

    @GetMapping("/usuarios")
    @ResponseBody
    public List<UsuarioDTO> filtrarUsuarios(@RequestParam String rol){
        return usuarioService.buscarUsuariosByRol(rol);
    }
}