package com.seguridadtech.dao;

import com.seguridadtech.conexion.Conexion;
import com.seguridadtech.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario validarLogin(String nombreUsuario, String contrasena) throws SQLException {
        String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.documento, u.correo, u.usuario, "
                + "u.contrasena, u.id_rol, r.nombre_rol, u.estado "
                + "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol "
                + "WHERE u.usuario = ? AND u.contrasena = ? AND u.estado = 'Activo'";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.documento, u.correo, u.usuario, "
                + "u.contrasena, u.id_rol, r.nombre_rol, u.estado "
                + "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol ORDER BY u.id_usuario";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }

    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombres, apellidos, documento, correo, usuario, contrasena, id_rol, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getDocumento());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getUsuario());
            ps.setString(6, usuario.getContrasena());
            ps.setInt(7, usuario.getIdRol());
            ps.setString(8, usuario.getEstado());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Usuario> listarTecnicos() throws SQLException {
        List<Usuario> tecnicos = new ArrayList<>();
        String sql = "SELECT u.id_usuario, u.nombres, u.apellidos, u.documento, u.correo, u.usuario, "
                + "u.contrasena, u.id_rol, r.nombre_rol, u.estado "
                + "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol "
                + "WHERE u.id_rol = 2 AND u.estado = 'Activo' ORDER BY u.nombres";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tecnicos.add(mapearUsuario(rs));
            }
        }
        return tecnicos;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("documento"),
                rs.getString("correo"),
                rs.getString("usuario"),
                rs.getString("contrasena"),
                rs.getInt("id_rol"),
                rs.getString("nombre_rol"),
                rs.getString("estado")
        );
    }
}
