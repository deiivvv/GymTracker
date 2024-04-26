package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

	@PersistenceContext
	private EntityManager entityManager;

	public PerfilDTO obtenerUsuariosConPerfil(int idUsuario) {
		String sql = "SELECT id, nombre, edad, genero, altura, peso" + " FROM usuarios u JOIN perfil p"
				+ " WHERE id= ? AND id=id_usuario " + " ORDER BY nombre;";

		PerfilDTO resultado = (PerfilDTO) entityManager.createNativeQuery(sql, PerfilDTO.class)
				.setParameter(1, idUsuario).getSingleResult();
        new PerfilDTO();
        return PerfilDTO.builder()
				.id(resultado.getId())
				.nombre(resultado.getNombre())
				.edad(resultado.getEdad())
				.genero(resultado.getGenero())
				.altura(resultado.getAltura())
				.peso(resultado.getPeso())
				.build();
	}

	@Transactional
	public void editarUsuario(PerfilDTO perfilDTO) {
		String sql = "UPDATE perfil AS p " + "JOIN usuarios AS u ON p.id_usuario = u.id "
				+ "SET p.edad = ?, p.genero = ?, p.peso = ?, p.altura = ?, u.nombre = ? " + "WHERE p.id_usuario = ?";

		entityManager.createNativeQuery(sql).setParameter(1, perfilDTO.getEdad())
				.setParameter(2, perfilDTO.getGenero())
				.setParameter(3, perfilDTO.getPeso())
				.setParameter(4, perfilDTO.getAltura())
				.setParameter(5, perfilDTO.getNombre())
				.setParameter(6, perfilDTO.getId()).executeUpdate();
	}

	@Transactional
	public void borrarUsuario(int idUsuario) {
		String deleteUsuarioSql = "DELETE FROM usuarios WHERE id = ?";
		entityManager.createNativeQuery(deleteUsuarioSql)
			.setParameter(1, idUsuario).executeUpdate();
	}

}
