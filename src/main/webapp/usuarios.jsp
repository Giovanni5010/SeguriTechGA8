<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.seguridadtech.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SeguriTech - Usuarios</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="header">
        <h1>Módulo de usuarios</h1>
        <p>Gestión básica de usuarios del sistema</p>
    </div>

    <div class="contenedor">
        <div class="nav-superior">
            <a class="btn btn-secundario" href="dashboard.jsp">Volver al panel</a>
        </div>

        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="mensaje"><%= request.getAttribute("mensaje") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>

        <h2>Registrar usuario</h2>
        <form action="UsuarioServlet" method="post">
            <input type="text" name="nombres" placeholder="Nombres" required>
            <input type="text" name="apellidos" placeholder="Apellidos" required>
            <input type="text" name="documento" placeholder="Documento" required>
            <input type="email" name="correo" placeholder="Correo electrónico" required>
            <input type="text" name="usuario" placeholder="Usuario de acceso" required>
            <input type="password" name="contrasena" placeholder="Contraseña" required>
            <select name="idRol" required>
                <option value="1">Administrador</option>
                <option value="2">Técnico</option>
            </select>
            <button type="submit">Guardar usuario</button>
        </form>

        <h2>Usuarios registrados</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Documento</th>
                    <th>Correo</th>
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                if (usuarios != null && !usuarios.isEmpty()) {
                    for (Usuario u : usuarios) {
            %>
                <tr>
                    <td><%= u.getIdUsuario() %></td>
                    <td><%= u.getNombres() %> <%= u.getApellidos() %></td>
                    <td><%= u.getDocumento() %></td>
                    <td><%= u.getCorreo() %></td>
                    <td><%= u.getUsuario() %></td>
                    <td><%= u.getNombreRol() %></td>
                    <td><%= u.getEstado() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="7">No hay usuarios registrados.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
