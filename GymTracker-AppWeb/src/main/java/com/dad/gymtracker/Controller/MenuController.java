package com.dad.gymtracker.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MenuController {

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/inicio")
    public String menu(@RequestBody String usuario, HttpSession session){
        session.setAttribute("usuario",usuario);
        return "menu";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "perfil";
    }

    @GetMapping("/mis-entrenamientos")
    public String misEntrenamientos(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "misEntrenamientos";
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
