package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilUsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

	@PersistenceContext
	private EntityManager entityManager;

	public PerfilUsuarioDTO obtenerUsuariosConPerfil(int idUsuario) {
		String sql = "SELECT id, nombre, edad, genero, altura, peso" + " FROM usuarios u JOIN perfil p"
				+ " WHERE id= ? AND id=id_usuario " + " ORDER BY nombre;";

		PerfilUsuarioDTO resultados = (PerfilUsuarioDTO) entityManager.createNativeQuery(sql, PerfilUsuarioDTO.class)
				.setParameter(1, idUsuario).getSingleResult();
		return resultados;
	}

	@Transactional
	public void editarUsuario(PerfilUsuarioDTO perfilUsuarioDTO) {
		String sql = "UPDATE perfil AS p " + "JOIN usuarios AS u ON p.id_usuario = u.id "
				+ "SET p.edad = ?, p.genero = ?, p.peso = ?, p.altura = ?, u.nombre = ? " + "WHERE p.id_usuario = ?";

		entityManager.createNativeQuery(sql).setParameter(1, perfilUsuarioDTO.getEdad())
				.setParameter(2, perfilUsuarioDTO.getGenero())
				.setParameter(3, perfilUsuarioDTO.getPeso())
				.setParameter(4, perfilUsuarioDTO.getAltura())
				.setParameter(5, perfilUsuarioDTO.getNombre())
				.setParameter(6, perfilUsuarioDTO.getId()).executeUpdate();
	}

	@Transactional
	public void borrarUsuario(int idUsuario) {
		String deleteUsuarioSql = "DELETE FROM usuarios WHERE id = ?";
		entityManager.createNativeQuery(deleteUsuarioSql)
			.setParameter(1, idUsuario).executeUpdate();
	}

}
