package com.dad.gymtracker.Controller;

import com.dad.gymtracker.Dto.EstadisticasUsuarioDTO;
import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.ClasificacionService;
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
@RequestMapping("/clasificacion")
@PreAuthorize("hasAuthority('CREATE')")
public class ClasificacionController {

    private final String RUTATEMPLAES = "clasificacion/";
    private final ClasificacionService clasificacionService;

    @GetMapping
    public String clasificacionUsuarios(@RequestParam(defaultValue = "entrenos") String estadistica, Model model, HttpSession session) {
        List<EstadisticasUsuarioDTO> listaUsuarios =clasificacionService.buscarAllEstadisticasUsuariosNoBloqueados();
        if(!listaUsuarios.isEmpty()) {
            clasificacionService.ordenarUsuariosByEstadistica(listaUsuarios, estadistica);
            model.addAttribute("listaUsuarios", listaUsuarios);
            double pos=clasificacionService.bucarPosicionById(listaUsuarios, (int) session.getAttribute("idUsuario"));
            model.addAttribute("pag", (int) Math.ceil(pos / 5));
            model.addAttribute("idUsuario", session.getAttribute("idUsuario"));
        }
        return RUTATEMPLAES + "listar";
    }

    @GetMapping("/ordenar")
    @ResponseBody
    public List<EstadisticasUsuarioDTO> listaUsuarios(@RequestParam(defaultValue = "entrenos") String estadistica){
        List<EstadisticasUsuarioDTO> listaUsuarios =clasificacionService.buscarAllEstadisticasUsuariosNoBloqueados();
        if(!listaUsuarios.isEmpty()) {
            clasificacionService.ordenarUsuariosByEstadistica(listaUsuarios, estadistica);
            return listaUsuarios;
        }
        return null;
    }
}