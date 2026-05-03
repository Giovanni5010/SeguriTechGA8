package com.seguridadtech.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase responsable de centralizar la conexión JDBC con MySQL.
 * Si MySQL en XAMPP usa el puerto 3307, hay que cambiarlo por  3306  en la URL.
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/seguritech_ga8?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL. Verifique la librería mysql-connector-j.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
