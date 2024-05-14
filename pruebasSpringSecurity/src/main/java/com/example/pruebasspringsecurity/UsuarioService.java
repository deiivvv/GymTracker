package com.example.pruebasspringsecurity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;
    public UsuarioDTO buscarUsuarioByNombre(String nombre){
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

    public List<UsuarioDTO> buscarAllUsuarios() {
        String sqlBuscarAllUsuarios = "SELECT id, nombre, contrasena, rol FROM usuarios ORDER BY id";

        try {
            List<UsuarioDTO> resultado =entityManager.createNativeQuery(sqlBuscarAllUsuarios, UsuarioDTO.class)
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
    public void cambiarRol(int id,String rol){
        String sqlCambiarRolUsuario = "UPDATE usuarios" + " SET rol = ?" + " WHERE id = ?";

        entityManager.createNativeQuery(sqlCambiarRolUsuario)
                .setParameter(1, rol)
                .setParameter(2, id)
                .executeUpdate();
    }


}
