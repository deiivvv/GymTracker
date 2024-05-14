package com.dad.gymtracker.Configuracion;

import com.dad.gymtracker.Dto.UsuarioDTO;
import com.dad.gymtracker.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UsuarioService usuarioService;
/*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //token de autentificacion?
                .authorizeHttpRequests(http -> {
                    //configurar endpoints publicos
                    http.requestMatchers(HttpMethod.GET, "/").permitAll();
                    //configurar endpoints privados
                    http.requestMatchers(HttpMethod.GET, "/menu").hasAnyAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/admin").hasAnyAuthority("CREATE");
                    //configurar el resto de endpoints - no especificados
                    //rechaza todo
                    http.anyRequest().denyAll();
                    //acceder  con usuario
                    *//*http.anyRequest().authenticated();*//*
                })
                .build();
    }*/


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //token de autentificacion?
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> userDetails= new ArrayList<>();

        List<UsuarioDTO> listUsuarioDTOS=  usuarioService.buscarAllUsuarios();
        List<String> permisos = new ArrayList<>();
        String rol="";
        for(UsuarioDTO usuarioDTO : listUsuarioDTOS) {
            switch (usuarioDTO.getRol()){
                case "usuario" :
                    permisos.add("READ");
                    break;
                case "admin":
                    permisos.add("READ");
                    permisos.add("CREATE");
                    break;
                case "bloqueado":
                    break;
            }

            UserDetails user= User.withUsername(usuarioDTO.getNombre())
                            .password(usuarioDTO.getContrasena())
                            .roles(usuarioDTO.getRol())
                            .authorities(permisos.toArray(new String[0]))
                            .build();
            userDetails.add(user);
        }
        /*userDetails.add( User.withUsername("alvaro")
                .password("alvaro")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());*/

        return new InMemoryUserDetailsManager(userDetails);
    }

    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
        /*return new BCryptPasswordEncoder();*/
    }
}


