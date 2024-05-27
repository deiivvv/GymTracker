package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void crearUsuario(UsuarioDTO usuarioDTO, PerfilDTO perfilUsuarioDTO) {
        try {
            String encodedPassword = passwordEncoder.encode(usuarioDTO.getContrasena());

            String sqlInsertUsuario = "INSERT INTO usuarios (nombre, contrasena, rol)" +
                    " VALUES (?, ?, ?);";

            String rol = "usuario";
            if (usuarioDTO.getRol() != null) {
                rol = usuarioDTO.getRol();
            }

            entityManager.createNativeQuery(sqlInsertUsuario)
                    .setParameter(1, usuarioDTO.getNombre())
                    .setParameter(2, encodedPassword)
                    .setParameter(3, rol)
                    .executeUpdate();

            String sqlInsertPerfil = "INSERT INTO perfil (altura, edad, peso, genero, id_usuario)" +
                    " VALUES (?, ?, ?, ?, (SELECT id FROM usuarios" +
                    " WHERE id =(select LAST_INSERT_ID())));";

            entityManager.createNativeQuery(sqlInsertPerfil)
                    .setParameter(1, perfilUsuarioDTO.getAltura())
                    .setParameter(2, perfilUsuarioDTO.getEdad())
                    .setParameter(3, perfilUsuarioDTO.getPeso())
                    .setParameter(4, perfilUsuarioDTO.getGenero())
                    .executeUpdate();

            // Crear UserDetails para el nuevo usuario (Spring Security)
            UserDetails user = User.withUsername(usuarioDTO.getNombre())
                    .password(encodedPassword)
                    .roles(rol.toUpperCase())
                    .build();

            // Agregar el nuevo usuario al InMemoryUserDetailsManager
            inMemoryUserDetailsManager.createUser(user);

        } catch (Exception e) {
            log.error("Error al crear el usuario: {}", e.getMessage());
        }
    }

    public UsuarioDTO buscarUsuarioByNombre(String nombre) {
        String sqlBuscarUsuario = "SELECT id, nombre, contrasena, rol FROM usuarios WHERE nombre = ?";
        try {
            UsuarioDTO resultado = (UsuarioDTO) entityManager.createNativeQuery(sqlBuscarUsuario, UsuarioDTO.class)
                    .setParameter(1, nombre)
                    .getSingleResult();

            return UsuarioDTO.builder()
                    .id(resultado.getId())
                    .nombre(resultado.getNombre())
                    .contrasena(resultado.getContrasena())
                    .rol(resultado.getRol())
                    .build();
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }

    public UsuarioDTO buscarUsuarioByNombreAndContrasena(String nombre, String contrasena) {
        String sqlBuscarUsuario = "SELECT id, nombre, contrasena, rol FROM usuarios WHERE nombre = ? AND contrasena = ?";

        try {
            UsuarioDTO resultado = (UsuarioDTO) entityManager.createNativeQuery(sqlBuscarUsuario, UsuarioDTO.class)
                    .setParameter(1, nombre)
                    .setParameter(2, contrasena)
                    .getSingleResult();

            return UsuarioDTO.builder()
                    .id(resultado.getId())
                    .nombre(resultado.getNombre())
                    .contrasena(resultado.getContrasena())
                    .rol(resultado.getRol())
                    .build();
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }

    public UsuarioDTO buscarUsuarioById(int id) {
        String sqlBuscarUsuario = "SELECT id, nombre, contrasena, rol FROM usuarios WHERE id = ?";

        try {
            UsuarioDTO resultado = (UsuarioDTO) entityManager.createNativeQuery(sqlBuscarUsuario, UsuarioDTO.class)
                    .setParameter(1, id)
                    .getSingleResult();

            return UsuarioDTO.builder()
                    .id(resultado.getId())
                    .nombre(resultado.getNombre())
                    .contrasena(resultado.getContrasena())
                    .rol(resultado.getRol())
                    .build();
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }

    public List<UsuarioDTO> buscarAllUsuarios() {
        String sqlBuscarAllUsuarios = "SELECT id, nombre, contrasena, rol FROM usuarios ORDER BY id";

        try {
            List<UsuarioDTO> resultado = entityManager.createNativeQuery(sqlBuscarAllUsuarios, UsuarioDTO.class)
                    .getResultList();

            return resultado.stream()
                    .map(t -> UsuarioDTO.builder()
                            .id(t.getId())
                            .nombre(t.getNombre())
                            .contrasena(t.getContrasena())
                            .rol(t.getRol())
                            .build())
                    .collect(Collectors.toList());
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }

    public List<UsuarioDTO> buscarUsuariosByRol(String rol) {
        String sqlBuscarUsuariosByRol = "SELECT id, nombre, contrasena, rol FROM usuarios WHERE rol like ? ORDER BY id";

        try {
            List<UsuarioDTO> resultado = entityManager.createNativeQuery(sqlBuscarUsuariosByRol, UsuarioDTO.class)
                    .setParameter(1, rol)
                    .getResultList();

            return resultado.stream()
                    .map(t -> UsuarioDTO.builder()
                            .id(t.getId())
                            .nombre(t.getNombre())
                            .contrasena(t.getContrasena())
                            .rol(t.getRol())
                            .build())
                    .collect(Collectors.toList());
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }

    @Transactional
    public void cambiarRol(int id, String rol) {
        String sqlCambiarRolUsuario = "UPDATE usuarios" + " SET rol = ?" + " WHERE id = ?";

        entityManager.createNativeQuery(sqlCambiarRolUsuario)
                .setParameter(1, rol)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Transactional
    public void cambiarContrasena(int id, String contrasena) {
        try {
            String encodedPassword = passwordEncoder.encode(contrasena);
            // Actualizar la contraseña en la base de datos
            String sqlCambiarContrasena = "UPDATE usuarios SET contrasena = ? WHERE id = ?";
            entityManager.createNativeQuery(sqlCambiarContrasena)
                    .setParameter(1, encodedPassword)
                    .setParameter(2, id)
                    .executeUpdate();

            // Recuperar el nombre de usuario basado en el ID
            String sqlObtenerNombre = "SELECT nombre, rol FROM usuarios WHERE id = ?";
            Object[] result = (Object[]) entityManager.createNativeQuery(sqlObtenerNombre)
                    .setParameter(1, id)
                    .getSingleResult();

            String nombreUsuario = (String) result[0];
            String rolUsuario = (String) result[1];

            // Actualizar la contraseña en el InMemoryUserDetailsManager
            UserDetails user = User.withUsername(nombreUsuario)
                    .password(encodedPassword)
                    .roles(rolUsuario.toUpperCase())
                    .build();

            inMemoryUserDetailsManager.updateUser(user);

        } catch (Exception e) {
            log.error("Error al cambiar la contraseña: {}", e.getMessage());
        }
    }


}
