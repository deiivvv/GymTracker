package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Slf4j
public class UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void crearUsuario(UsuarioDTO usarioDTO, PerfilDTO perfilUsuarioDTO) {
        String sqlInsertUsuario = "INSERT INTO usuarios (nombre, contrasena, rol)" +
                " VALUES (?, ?, 'usuario');";

        entityManager.createNativeQuery(sqlInsertUsuario).setParameter(1, usarioDTO.getNombre())
                .setParameter(2, usarioDTO.getContrasena())
                .executeUpdate();

        String sqlInsertPeril = "INSERT INTO perfil (altura, edad, peso, genero, id_usuario)" +
                " VALUES (?, ?, ?, ?, (SELECT id FROM usuarios" +
                " WHERE id =" +
                " (select LAST_INSERT_ID())));";

        entityManager.createNativeQuery(sqlInsertPeril).setParameter(1, perfilUsuarioDTO.getAltura())
                .setParameter(2, perfilUsuarioDTO.getEdad())
                .setParameter(3, perfilUsuarioDTO.getPeso())
                .setParameter(4, perfilUsuarioDTO.getGenero())
                .executeUpdate();
    }
    
    public UsuarioDTO buscarUsuario(String nombre, String contrasena) {
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

}
