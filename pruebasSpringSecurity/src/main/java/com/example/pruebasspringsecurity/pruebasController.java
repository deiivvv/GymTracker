package com.example.pruebasspringsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pruebasController {

    @GetMapping("/")
    public String a(){
        return "hola";
    }
}
