package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilUsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerfilService {

    @PersistenceContext
    private EntityManager entityManager;

    public PerfilUsuarioDTO obtenerUsuariosConPerfil(int idUsuario) {
        String sql = "SELECT u.id AS id, u.nombre AS nombre, p.edad AS edad, p.altura AS altura" +
                " FROM usuarios u" +
                " JOIN perfil p WHERE u.id = " + idUsuario +
                " AND u.id=p.id_usuario" +
                " ORDER BY u.nombre";

        Query query = entityManager.createNativeQuery(sql, PerfilUsuarioDTO.class);
        PerfilUsuarioDTO resultados = (PerfilUsuarioDTO) query.getSingleResult();
        return resultados;
    }
}

