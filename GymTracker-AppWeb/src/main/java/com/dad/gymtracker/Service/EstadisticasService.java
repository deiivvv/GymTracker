package com.dad.gymtracker.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dad.gymtracker.Dto.PerfilDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
	    
	    public Long numeroEntrenamientos(Integer idUsuario) {
	        String sql = "SELECT COUNT(*) FROM rutinas WHERE id_usuario = :idUsuario";
	        return (Long) entityManager.createNativeQuery(sql)
	                                   .setParameter("idUsuario", idUsuario)
	                                   .getSingleResult();
	    }
	    
	    public Long numeroEjercicios(Integer idUsuario) {
	        String sql = "SELECT COUNT(DISTINCT id_ejercicio) FROM rutinas_ejercicios_series res " +
	                     "JOIN rutinas r ON res.id_rutina = r.id " +
	                     "WHERE r.id_usuario = :idUsuario";
	        return (Long) entityManager.createNativeQuery(sql)
	                                   .setParameter("idUsuario", idUsuario)
	                                   .getSingleResult();
	    }
	    
	    public String ejercicioFavorito(Integer idUsuario) {
	    	try {
	        String sql = "SELECT e.nombre, COUNT(*) AS total " +
	                     "FROM ejercicios e " +
	                     "JOIN rutinas_ejercicios_series res ON e.id = res.id_ejercicio " +
	                     "JOIN rutinas r ON res.id_rutina = r.id " +
	                     "WHERE r.id_usuario = :idUsuario " +
	                     "GROUP BY e.nombre " +
	                     "ORDER BY total DESC " +
	                     "LIMIT 1";
	        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
	                                                 .setParameter("idUsuario", idUsuario)
	                                                 .getSingleResult();
	        return result != null ? (String) result[0] : null;
	    	} catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    
	    public Double volumen(Integer idUsuario) {
	        String sql = "SELECT SUM(res.peso * res.repes) AS volumen " +
	                     "FROM series res " +
	                     "JOIN rutinas_ejercicios_series re ON res.id = re.id_serie " +
	                     "JOIN rutinas r ON re.id_rutina = r.id " +
	                     "WHERE r.id_usuario = :idUsuario";
	        return (Double) entityManager.createNativeQuery(sql)
	                                   .setParameter("idUsuario", idUsuario)
	                                   .getSingleResult();
	    }
	    
	    
	    public Long numeroSeries(Integer idUsuario) {
	        String sql = "SELECT COUNT(*) " +
	                     "FROM rutinas_ejercicios_series res " +
	                     "JOIN rutinas r ON res.id_rutina = r.id " +
	                     "WHERE r.id_usuario = :idUsuario";
	        return (Long) entityManager.createNativeQuery(sql)
	                                   .setParameter("idUsuario", idUsuario)
	                                   .getSingleResult();
	    }
	    
	    
	    

}
