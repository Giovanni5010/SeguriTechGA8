package com.seguridadtech.dao;

import com.seguridadtech.conexion.Conexion;
import com.seguridadtech.modelo.VisitaTecnica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitaTecnicaDAO {

    public List<VisitaTecnica> listarVisitas() throws SQLException {
        List<VisitaTecnica> visitas = new ArrayList<>();
        String sql = "SELECT v.id_visita, v.id_cliente, v.id_usuario, c.nombre_cliente, "
                + "CONCAT(u.nombres, ' ', u.apellidos) AS nombre_tecnico, "
                + "v.fecha_visita, v.motivo, v.descripcion, v.observaciones, v.estado "
                + "FROM visita_tecnica v "
                + "INNER JOIN cliente c ON v.id_cliente = c.id_cliente "
                + "INNER JOIN usuario u ON v.id_usuario = u.id_usuario "
                + "ORDER BY v.id_visita DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                visitas.add(new VisitaTecnica(
                        rs.getInt("id_visita"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_cliente"),
                        rs.getString("nombre_tecnico"),
                        rs.getString("fecha_visita"),
                        rs.getString("motivo"),
                        rs.getString("descripcion"),
                        rs.getString("observaciones"),
                        rs.getString("estado")
                ));
            }
        }
        return visitas;
    }

    public boolean registrarVisita(VisitaTecnica visita) throws SQLException {
        String sql = "INSERT INTO visita_tecnica (id_cliente, id_usuario, fecha_visita, motivo, descripcion, observaciones, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, visita.getIdCliente());
            ps.setInt(2, visita.getIdUsuario());
            ps.setString(3, visita.getFechaVisita());
            ps.setString(4, visita.getMotivo());
            ps.setString(5, visita.getDescripcion());
            ps.setString(6, visita.getObservaciones());
            ps.setString(7, visita.getEstado());
            return ps.executeUpdate() > 0;
        }
    }
}
