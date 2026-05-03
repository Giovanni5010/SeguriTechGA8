package com.seguridadtech.servlet;

import com.seguridadtech.dao.ClienteDAO;
import com.seguridadtech.modelo.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cargarClientes(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(request.getParameter("nombreCliente"));
        cliente.setDocumentoNit(request.getParameter("documentoNit"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setCorreo(request.getParameter("correo"));
        cliente.setEstado("Activo");

        try {
            clienteDAO.registrarCliente(cliente);
            request.setAttribute("mensaje", "Cliente registrado correctamente.");
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo registrar el cliente: " + e.getMessage());
        }
        cargarClientes(request, response);
    }

    private void cargarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Cliente> clientes = clienteDAO.listarClientes();
            request.setAttribute("clientes", clientes);
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudieron listar los clientes: " + e.getMessage());
        }
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }
}
