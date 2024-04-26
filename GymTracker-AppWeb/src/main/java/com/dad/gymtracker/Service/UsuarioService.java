package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilUsuarioDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import jakarta.persistence.EntityManager;
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
    public void crearUsuario(UsuarioDTO usarioDTO, PerfilUsuarioDTO perfilUsuarioDTO) {
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
}
