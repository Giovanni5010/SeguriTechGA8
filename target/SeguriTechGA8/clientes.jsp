<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.seguridadtech.modelo.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SeguriTech - Clientes</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="header">
        <h1>Módulo de clientes</h1>
        <p>Registro y consulta de clientes vinculados al servicio</p>
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

        <h2>Registrar cliente</h2>
        <form action="ClienteServlet" method="post">
            <input type="text" name="nombreCliente" placeholder="Nombre del cliente" required>
            <input type="text" name="documentoNit" placeholder="Documento o NIT" required>
            <input type="text" name="direccion" placeholder="Dirección" required>
            <input type="text" name="telefono" placeholder="Teléfono" required>
            <input type="email" name="correo" placeholder="Correo electrónico">
            <button type="submit">Guardar cliente</button>
        </form>

        <h2>Clientes registrados</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Documento/NIT</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                if (clientes != null && !clientes.isEmpty()) {
                    for (Cliente c : clientes) {
            %>
                <tr>
                    <td><%= c.getIdCliente() %></td>
                    <td><%= c.getNombreCliente() %></td>
                    <td><%= c.getDocumentoNit() %></td>
                    <td><%= c.getDireccion() %></td>
                    <td><%= c.getTelefono() %></td>
                    <td><%= c.getCorreo() %></td>
                    <td><%= c.getEstado() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="7">No hay clientes registrados.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
