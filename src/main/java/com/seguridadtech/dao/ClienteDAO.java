package com.seguridadtech.dao;

import com.seguridadtech.conexion.Conexion;
import com.seguridadtech.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre_cliente, documento_nit, direccion, telefono, correo, estado "
                + "FROM cliente ORDER BY id_cliente";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("documento_nit"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("estado")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public boolean registrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nombre_cliente, documento_nit, direccion, telefono, correo, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getDocumentoNit());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getEstado());
            return ps.executeUpdate() > 0;
        }
    }
}
