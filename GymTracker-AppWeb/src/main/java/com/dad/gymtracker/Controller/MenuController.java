package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @RequestMapping("/inicio")
    public String menu(@RequestBody String usuario,
                       HttpSession session){

        /*cosas de bbdd y  login*/
        session.setAttribute("idUsuario", 8);
        return "menu";

    }

    @GetMapping("/mis-entrenamientos")
    public String misEntrenamientos(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "misEntrenamientos";
    }
    @GetMapping("/ejercicios")
    public String ejercicios(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "ejercicios";
    }

    @GetMapping("/cerrar-sesion")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
