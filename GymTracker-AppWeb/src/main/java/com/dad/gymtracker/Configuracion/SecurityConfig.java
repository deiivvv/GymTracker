package com.dad.gymtracker.Configuracion;



import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UsuarioService usuarioService;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                		.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                		 .requestMatchers("/inicio/**").hasAnyRole("USER", "ADMIN")
                		  
                        .anyRequest().authenticated()
                                
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                		.loginPage("/")
                        .permitAll())
        		.logout(logout -> logout
        				.logoutSuccessUrl("/")
        				.permitAll()
        		);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UsuarioDTO> usuarioDTOList = usuarioService.buscarAllUsuarios();
        List<UserDetails> userDetails= new ArrayList<>();
        for(UsuarioDTO usuarioDTO : usuarioDTOList){
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username(usuarioDTO.getNombre())
                    .password(usuarioDTO.getContrasena())
                    .roles("USER")
                    .build();
            userDetails.add(user);
        }
        return new InMemoryUserDetailsManager(userDetails);
    }

}

