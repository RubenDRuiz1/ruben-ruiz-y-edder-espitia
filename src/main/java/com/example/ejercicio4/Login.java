package com.example.ejercicio4;

import com.example.ejercicio4.Conexion;
import com.example.ejercicio4.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public void registrarUsuario(Usuario usuario) {
        if (nombreUsuarioExiste(usuario.getUsername())) {
            System.out.println("Error: El nombre de usuario ya está en uso.");
        } else {
            guardarUsuario(usuario);
        }
    }

    public boolean nombreUsuarioExiste(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nombreUsuario);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el nombre de usuario: " + e.getMessage());
        }
        return false;
    }


    private void guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    
}
