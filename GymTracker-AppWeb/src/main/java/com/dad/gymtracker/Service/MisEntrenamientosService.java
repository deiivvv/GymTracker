package com.dad.gymtracker.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.dad.gymtracker.Dto.MisEntrenamientosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Service
public class MisEntrenamientosService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<MisEntrenamientosDTO> buscarEntrenamientosById(int id) {
        String sqlBuscarMisEntrenamientos = "SELECT * FROM rutinas WHERE id = ? ORDER BY id;";

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

	
}
