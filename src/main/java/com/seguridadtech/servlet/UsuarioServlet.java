package com.seguridadtech.servlet;

import com.seguridadtech.dao.UsuarioDAO;
import com.seguridadtech.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cargarUsuarios(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNombres(request.getParameter("nombres"));
        usuario.setApellidos(request.getParameter("apellidos"));
        usuario.setDocumento(request.getParameter("documento"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setContrasena(request.getParameter("contrasena"));
        usuario.setIdRol(Integer.parseInt(request.getParameter("idRol")));
        usuario.setEstado("Activo");

        try {
            usuarioDAO.registrarUsuario(usuario);
            request.setAttribute("mensaje", "Usuario registrado correctamente.");
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo registrar el usuario: " + e.getMessage());
        }
        cargarUsuarios(request, response);
    }

    private void cargarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudieron listar los usuarios: " + e.getMessage());
        }
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }
}
