package com.dad.gymtracker.Service;

import org.springframework.stereotype.Service;

import com.dad.gymtracker.Dto.MisEntrenamientosDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarioService {

	@PersistenceContext
    private EntityManager entityManager;
	
	 public MisEntrenamientosDTO buscarFechasById(int id) {
	    	String sqlBuscarRutina = "SELECT id_usuario AS id,\n"
	    			+ "       GROUP_CONCAT(nombre ORDER BY nombre ASC SEPARATOR ',') AS nombre,\n"
	    			+ "       GROUP_CONCAT(fecha ORDER BY fecha ASC SEPARATOR ',') AS fecha\n"
	    			+ "FROM rutinas\n"
	    			+ "WHERE id_usuario = ?;";

	    	MisEntrenamientosDTO resultado = (MisEntrenamientosDTO) entityManager.createNativeQuery(sqlBuscarRutina, MisEntrenamientosDTO.class)
	                .setParameter(1, id)
	                .getSingleResult();

	        return MisEntrenamientosDTO.builder()
	        		.id(resultado.getId())
	        		.nombre(resultado.getNombre())
					.fecha(resultado.getFecha())
					.build();
	 }
}
