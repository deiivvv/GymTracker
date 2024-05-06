package com.dad.gymtracker.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.dad.gymtracker.Dto.NuevaRutinaDTO;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Slf4j
@Service
public class RutinaService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MisEntrenamientosDTO> buscarEntrenamientosById(int id) {
        String sqlBuscarMisEntrenamientos = "SELECT * FROM rutinas WHERE id_usuario = ? ORDER BY id";

        List<MisEntrenamientosDTO> resultado = entityManager.createNativeQuery(sqlBuscarMisEntrenamientos, MisEntrenamientosDTO.class)
                .setParameter(1, id)
                .getResultList();

        return resultado.stream()
                .map(t -> MisEntrenamientosDTO.builder()
                        .id(t.getId())
                        .nombre(t.getNombre())
                        .fecha(t.getFecha())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void crearRutina(NuevaRutinaDTO nuevaRutinaDTO, Integer idUsuario) {
        try {
            insertarEjercicios(nuevaRutinaDTO.getEjercicios());
            insertarRutina(idUsuario, nuevaRutinaDTO.getNombre());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    throws SQLException
    @Transactional
    public void insertarEjercicios(String ejercicios) throws SQLException {
        String[] ejerciciosArray = ejercicios.split(",");

        String sqlSelectIdExistente = "SELECT COUNT(*) FROM ejercicios WHERE id = ?";
        String sqlInsertEjercicio = "INSERT INTO ejercicios (id, nombre) VALUES (?, ?)";

        for (String ejercicio : ejerciciosArray) {
            String[] ejercicioData = ejercicio.split("@");
            String id = ejercicioData[0];
            String nombre = ejercicioData[1];

            // Verificar si la ID ya existe
            Long count = (Long) entityManager.createNativeQuery(sqlSelectIdExistente)
                    .setParameter(1, id)
                    .getSingleResult();

            if (count == 0) { // La ID no existe, entonces insertar el ejercicio
                entityManager.createNativeQuery(sqlInsertEjercicio)
                        .setParameter(1, id)
                        .setParameter(2, nombre)
                        .executeUpdate();
            } else {
                //mensaje por consola
                log.warn("La ID " + id + " ya existe, no se insertará el ejercicio.");
            }
        }
    }

    @Transactional
    public void insertarRutina(Integer idUsuario, String nombre) throws SQLException {
        entityManager.createNativeQuery("INSERT INTO rutinas (id_usuario, nombre, fecha) VALUES (?, ?, CURDATE())")
                .setParameter(1, idUsuario)
                .setParameter(2, nombre)
                .executeUpdate();
    }


}
