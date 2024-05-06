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
            Integer idRutina=insertarRutina(idUsuario, nuevaRutinaDTO.getNombre());
            insertarEjercicios(idRutina,nuevaRutinaDTO.getEjercicios());
//            insertarSeries();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Integer insertarRutina(Integer idUsuario, String nombre) throws SQLException {

        String sqlInsertRutina = "INSERT INTO rutinas (id_usuario, nombre, fecha) VALUES (?, ?, CURDATE())";

        entityManager.createNativeQuery(sqlInsertRutina)
                .setParameter(1, idUsuario)
                .setParameter(2, nombre)
                .executeUpdate();

        // Obtener el ID de la rutina recién insertada
        Long idGenerada = (Long) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()")
                .getSingleResult();

        return idGenerada.intValue();
    }

    @Transactional
    public void insertarEjercicios(Integer idRutina,String ejercicios) throws SQLException {
        String[] ejerciciosArray = ejercicios.split(",");

        String sqlSelectIdExistente = "SELECT COUNT(*) FROM ejercicios WHERE id = ?";
        String sqlInsertEjercicio = "INSERT INTO ejercicios (id, nombre) VALUES (?, ?)";
        String sqlInsertRelacion = "INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio) VALUES (?, ?)";

        for (String ejercicio : ejerciciosArray) {
            String[] ejercicioData = ejercicio.split("@");
            String idEjercicio = ejercicioData[0];
            String nombre = ejercicioData[1];

            // Verificar si la ID ya existe
            Long count = (Long) entityManager.createNativeQuery(sqlSelectIdExistente)
                    .setParameter(1, idEjercicio)
                    .getSingleResult();

            if (count == 0) { // La ID no existe, entonces insertar el ejercicio
                entityManager.createNativeQuery(sqlInsertEjercicio)
                        .setParameter(1, idEjercicio)
                        .setParameter(2, nombre)
                        .executeUpdate();
            } else {
                //mensaje por consola
                log.warn("La ID " + idEjercicio + " ya existe, no se insertará el ejercicio.");
            }

            // Insertar la relación entre la rutina y el ejercicio
            entityManager.createNativeQuery(sqlInsertRelacion)
                    .setParameter(1, idRutina)
                    .setParameter(2, idEjercicio)
                    .executeUpdate();
        }
    }

}
