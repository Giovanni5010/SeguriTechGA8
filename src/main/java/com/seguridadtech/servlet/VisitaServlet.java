package com.seguridadtech.servlet;

import com.seguridadtech.dao.ClienteDAO;
import com.seguridadtech.dao.UsuarioDAO;
import com.seguridadtech.dao.VisitaTecnicaDAO;
import com.seguridadtech.modelo.Cliente;
import com.seguridadtech.modelo.Usuario;
import com.seguridadtech.modelo.VisitaTecnica;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VisitaServlet", urlPatterns = {"/VisitaServlet"})
public class VisitaServlet extends HttpServlet {

    private final VisitaTecnicaDAO visitaDAO = new VisitaTecnicaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cargarDatos(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VisitaTecnica visita = new VisitaTecnica();
        visita.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        visita.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        visita.setFechaVisita(request.getParameter("fechaVisita"));
        visita.setMotivo(request.getParameter("motivo"));
        visita.setDescripcion(request.getParameter("descripcion"));
        visita.setObservaciones(request.getParameter("observaciones"));
        visita.setEstado("Registrada");

        try {
            visitaDAO.registrarVisita(visita);
            request.setAttribute("mensaje", "Visita técnica registrada correctamente.");
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo registrar la visita técnica: " + e.getMessage());
        }
        cargarDatos(request, response);
    }

    private void cargarDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<VisitaTecnica> visitas = visitaDAO.listarVisitas();
            List<Cliente> clientes = clienteDAO.listarClientes();
            List<Usuario> tecnicos = usuarioDAO.listarTecnicos();
            request.setAttribute("visitas", visitas);
            request.setAttribute("clientes", clientes);
            request.setAttribute("tecnicos", tecnicos);
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudieron cargar los datos: " + e.getMessage());
        }
        request.getRequestDispatcher("visitas.jsp").forward(request, response);
    }
}
