<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SeguriTech - Inicio de Sesión</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="login-box">
        <h1>SeguriTech S.A.</h1>
        <p>Acceso al sistema de chequeo de cuentas</p>

        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="error"><%= request.getAttribute("mensajeError") %></div>
        <% } %>

        <form action="LoginServlet" method="post">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="contrasena" placeholder="Contraseña" required>
            <button type="submit">Ingresar</button>
        </form>

        <p>Usuario de prueba: admin / admin123</p>
    </div>
</body>
</html>
