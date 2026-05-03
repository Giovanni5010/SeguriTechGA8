package com.seguridadtech.servlet;

import com.seguridadtech.dao.UsuarioDAO;
import com.seguridadtech.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuarioValidado = usuarioDAO.validarLogin(usuario, contrasena);
            if (usuarioValidado != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSesion", usuarioValidado);
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "Error al validar el acceso: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
