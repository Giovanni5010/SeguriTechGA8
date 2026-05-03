<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.seguridadtech.modelo.Usuario" %>
<%
    Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSesion");
    if (usuarioSesion == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SeguriTech - Panel Principal</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="header">
        <h1>SeguriTech S.A.</h1>
        <p>Bienvenido, <%= usuarioSesion.getNombres() %> <%= usuarioSesion.getApellidos() %> - Rol: <%= usuarioSesion.getNombreRol() %></p>
    </div>

    <div class="contenedor">
        <h2>Panel principal</h2>
        <p>Seleccione el módulo que desea gestionar.</p>

        <div class="grid-menu">
            <div class="card-menu">
                <h3>Usuarios</h3>
                <p>Consulta y registro básico de usuarios del sistema.</p>
                <a class="btn" href="UsuarioServlet">Abrir módulo</a>
            </div>

            <div class="card-menu">
                <h3>Clientes</h3>
                <p>Registro y consulta de clientes asociados al servicio.</p>
                <a class="btn" href="ClienteServlet">Abrir módulo</a>
            </div>

            <div class="card-menu">
                <h3>Visitas técnicas</h3>
                <p>Registro de chequeos, visitas e historial de atención.</p>
                <a class="btn" href="VisitaServlet">Abrir módulo</a>
            </div>
        </div>

        <p style="margin-top: 24px;"><a class="btn btn-secundario" href="LogoutServlet">Cerrar sesión</a></p>
    </div>
</body>
</html>
