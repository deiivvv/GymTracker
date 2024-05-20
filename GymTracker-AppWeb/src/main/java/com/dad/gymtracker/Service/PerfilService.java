package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.PerfilDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@AllArgsConstructor
@Slf4j
@Service
public class PerfilService {

	@PersistenceContext
	private EntityManager entityManager;
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
	private final PasswordEncoder passwordEncoder;

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
		try {
			// Actualizar el perfil y el nombre de usuario en la base de datos
			String sql = "UPDATE perfil AS p "
					+ "JOIN usuarios AS u ON p.id_usuario = u.id "
					+ "SET p.edad = ?, p.genero = ?, p.peso = ?, p.altura = ?, u.nombre = ? "
					+ "WHERE p.id_usuario = ?";

			entityManager.createNativeQuery(sql)
					.setParameter(1, perfilDTO.getEdad())
					.setParameter(2, perfilDTO.getGenero())
					.setParameter(3, perfilDTO.getPeso())
					.setParameter(4, perfilDTO.getAltura())
					.setParameter(5, perfilDTO.getNombre())
					.setParameter(6, perfilDTO.getId())
					.executeUpdate();

			// Recuperar el nombre de usuario y el rol basado en el ID
			String sqlObtenerDatos = "SELECT u.nombre, u.contrasena, u.rol FROM usuarios AS u WHERE u.id = ?";
			Object[] resultado = (Object[]) entityManager.createNativeQuery(sqlObtenerDatos)
					.setParameter(1, perfilDTO.getId())
					.getSingleResult();
			String nombreUsuarioAnterior = (String) resultado[0];
			String contrasenaAnterior = (String) resultado[1];
			String rolUsuario = (String) resultado[2];

			// Eliminar el usuario antiguo del InMemoryUserDetailsManager
			inMemoryUserDetailsManager.deleteUser(nombreUsuarioAnterior);

			// Construir un nuevo UserDetails con el nuevo nombre de usuario y la contraseña existente
			UserDetails updatedUser = User.withUsername(perfilDTO.getNombre())
					.password(contrasenaAnterior) // Mantener la contraseña existente
					.roles(rolUsuario.toUpperCase()) // Mantener los roles existentes
					.build();

			// Agregar el usuario actualizado al InMemoryUserDetailsManager
			inMemoryUserDetailsManager.createUser(updatedUser);
		} catch (Exception e) {
			log.error("Error al editar el usuario: {}", e.getMessage());
		}
	}

	@Transactional
	public void borrarUsuario(int idUsuario) {
		try {
			// Recuperar el nombre de usuario basado en el ID
			String sqlObtenerNombre = "SELECT nombre FROM usuarios WHERE id = ?";
			String nombreUsuario = (String) entityManager.createNativeQuery(sqlObtenerNombre)
					.setParameter(1, idUsuario)
					.getSingleResult();

			// Eliminar al usuario de la base de datos
			String deleteUsuarioSql = "DELETE FROM usuarios WHERE id = ?";
			entityManager.createNativeQuery(deleteUsuarioSql)
					.setParameter(1, idUsuario)
					.executeUpdate();

			// Eliminar al usuario del InMemoryUserDetailsManager
			inMemoryUserDetailsManager.deleteUser(nombreUsuario);
		} catch (Exception e) {
			log.error("Error al borrar el usuario: {}", e.getMessage());
		}
	}


}
