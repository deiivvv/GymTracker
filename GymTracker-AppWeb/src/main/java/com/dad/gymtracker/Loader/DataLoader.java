package com.dad.gymtracker.Loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UsuarioService usuarioService, InMemoryUserDetailsManager inMemoryUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<UsuarioDTO> usuarioDTOList = usuarioService.buscarAllUsuarios();
        for (UsuarioDTO usuarioDTO : usuarioDTOList) {
            UserDetails user = User.withUsername(usuarioDTO.getNombre())
/*                    .password(passwordEncoder.encode(usuarioDTO.getContrasena()))*/
                    .password(usuarioDTO.getContrasena())
                    .roles(usuarioDTO.getRol().toUpperCase())
                    .build();
            inMemoryUserDetailsManager.createUser(user);
        }
    }
}
