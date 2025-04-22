package com.example.ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection getConexion() {
    	String conexionUrl = "jdbc:sqlserver://LAPTOP-504LJFES;databaseName=ejercicio;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        try {
            // Cargar la biblioteca de autenticación
            System.loadLibrary("mssql-jdbc_auth-12.8.1.x64"); // Asegúrate de que el nombre sea correcto
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch (SQLException ex) {
            System.out.println("Error al conectar: " + ex.toString());
            return null;
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Error al cargar la biblioteca de autenticación: " + e.toString());
            return null;
        }
    }
}