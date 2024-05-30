package com.dad.gymtracker.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dad.gymtracker.Dto.PerfilDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@AllArgsConstructor
@Slf4j
@Service
public class EstadisticasService {

	 @PersistenceContext
	 private EntityManager entityManager;

	    public Long contarEjerciciosGrupoMuscular(Integer idUsuario, String ids) {
	        String sql = "SELECT COUNT(re.id_ejercicio) AS total_ejercicios "
	                   + "FROM rutinas_ejercicios_series re "
	                   + "JOIN rutinas r ON re.id_rutina = r.id "
	                   + "WHERE r.id_usuario = :idUsuario "
	                   + "AND re.id_ejercicio IN (" + ids + ")";

	        Long totalEjercicios = ((Number) entityManager.createNativeQuery(sql)
	                                                      .setParameter("idUsuario", idUsuario)
	                                                      .getSingleResult())
	                                                      .longValue();

	        return totalEjercicios;
	    }

}
