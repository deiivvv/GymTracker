package com.dad.gymtracker.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.dad.gymtracker.Dto.NuevaRutinaDTO;
import com.dad.gymtracker.Dto.PerfilDTO;

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
    
    public NuevaRutinaDTO buscarRutinaById(int id) {
    	String sqlBuscarRutina = "SELECT rutinas.nombre AS nombre_rutina, " +
                "GROUP_CONCAT(DISTINCT CONCAT(ejercicios.id, '@', ejercicios.nombre) SEPARATOR ',') AS ejercicios, " +
                "GROUP_CONCAT(CONCAT(ejercicios.id, '@', series.peso, ':', series.repes) SEPARATOR ',') AS series " +
                "FROM rutinas " +
                "JOIN rutinas_ejercicios_series ON rutinas.id = rutinas_ejercicios_series.id_rutina " +
                "JOIN ejercicios ON rutinas_ejercicios_series.id_ejercicio = ejercicios.id " +
                "JOIN series ON rutinas_ejercicios_series.id_serie = series.id " +
                "WHERE rutinas.id = ? " +
                "GROUP BY rutinas.id";



    	NuevaRutinaDTO resultado = (NuevaRutinaDTO) entityManager.createNativeQuery(sqlBuscarRutina, NuevaRutinaDTO.class)
                .setParameter(1, id)
                .getSingleResult();

        return NuevaRutinaDTO.builder()
				.nombre(resultado.getNombre())
				.ejercicios(resultado.getEjercicios())
				.series(resultado.getSeries())
				.build();
    }

    @Transactional
    public void crearRutina(NuevaRutinaDTO nuevaRutinaDTO, Integer idUsuario) {
        try {
            Integer idRutina=insertarRutina(idUsuario, nuevaRutinaDTO.getNombre());
            insertarEjercicios(nuevaRutinaDTO.getEjercicios());
            insertarSeries(idRutina,nuevaRutinaDTO.getSeries());
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
    public void insertarEjercicios(String ejercicios) throws SQLException {
        String[] ejerciciosArray = ejercicios.split(",");

        String sqlSelectIdExistente = "SELECT COUNT(*) FROM ejercicios WHERE id = ?";
        String sqlInsertEjercicio = "INSERT INTO ejercicios (id, nombre) VALUES (?, ?)";

        for (String ejercicio : ejerciciosArray) {
            String[] ejercicioData = ejercicio.split("@");
            String idEjercicio = ejercicioData[0];
            String nombre = ejercicioData[1];

            // Verificar si la ID del ejercicio ya existe
            Long count = (Long) entityManager.createNativeQuery(sqlSelectIdExistente)
                    .setParameter(1, idEjercicio)
                    .getSingleResult();

            if (count == 0) { // La ID no existe, entonces insertar el ejercicio
                entityManager.createNativeQuery(sqlInsertEjercicio)
                        .setParameter(1, idEjercicio)
                        .setParameter(2, nombre)
                        .executeUpdate();
            } else {
                // Mensaje por consola
                log.warn("La ID " + idEjercicio + " ya existe, no se insertará el ejercicio.");
            }
        }
    }

    
    @Transactional
    public void insertarSeries(Integer idRutina, String series) throws SQLException{
        String sqlInsertSerie = "INSERT INTO series (peso, repes) VALUES (?, ?)";
        String sqlInsertEjercicioSerie = "INSERT INTO rutinas_ejercicios_series (id_rutina,id_ejercicio, id_serie) VALUES(?,?,?)";

        String[] seriesArray = series.split(",");

        for (String serie : seriesArray) {
            String[] partes = serie.split("@|:");
            int idEjercicio = Integer.parseInt(partes[0]);
            float peso = Float.parseFloat(partes[1]);
            int repes = Integer.parseInt(partes[2]);

            // Insertar la serie
            entityManager.createNativeQuery(sqlInsertSerie)
                    .setParameter(1, peso)
                    .setParameter(2, repes)
                    .executeUpdate();

            // Obtener el ID de la serie recién insertada
            Long idSerie = (Long)entityManager.createNativeQuery("SELECT LAST_INSERT_ID()")
                    .getSingleResult();

            // Establecer la relación entre rutina , ejercicio y serie
            entityManager.createNativeQuery(sqlInsertEjercicioSerie)
            		.setParameter(1, idRutina)        
            		.setParameter(2, idEjercicio)
                    .setParameter(3, idSerie)
                    .executeUpdate();
        }
    }

}
