package com.dad.gymtracker.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.stereotype.Service;
/*

import com.dad.gymtracker.Dto.Usuario;
import com.dad.gymtracker.Respository.UsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
*/


@Service
/*@Slf4j*/
//@AllArgsConstructor 
//@NoArgsConstructor
//@RequiredArgsConstructor
public class UsuarioService {
	
private Connection conexion;
	
	
	public void crearUsuario(String nombreUsuario, String contrasenaHash) {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)");
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasenaHash);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para autenticar un usuario
    public boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT contrasena FROM usuarios WHERE nombre_usuario = ?");
            statement.setString(1, nombreUsuario);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String contrasenaHash = result.getString("contrasena");
                // Aquí debes comparar la contraseña ingresada por el usuario con la contraseña almacenada en la base de datos
                // Si coinciden, devuelve true; de lo contrario, devuelve false
                return contrasena.equals(contrasenaHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
