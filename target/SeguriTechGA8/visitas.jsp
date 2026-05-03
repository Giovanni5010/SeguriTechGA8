<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.seguridadtech.modelo.Cliente" %>
<%@ page import="com.seguridadtech.modelo.Usuario" %>
<%@ page import="com.seguridadtech.modelo.VisitaTecnica" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SeguriTech - Visitas Técnicas</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="header">
        <h1>Módulo de visitas técnicas</h1>
        <p>Registro de chequeos e historial de atención</p>
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

        <h2>Registrar visita técnica</h2>
        <form action="VisitaServlet" method="post">
            <label>Cliente</label>
            <select name="idCliente" required>
                <%
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                    if (clientes != null) {
                        for (Cliente c : clientes) {
                %>
                    <option value="<%= c.getIdCliente() %>"><%= c.getNombreCliente() %></option>
                <%      }
                    }
                %>
            </select>

            <label>Técnico responsable</label>
            <select name="idUsuario" required>
                <%
                    List<Usuario> tecnicos = (List<Usuario>) request.getAttribute("tecnicos");
                    if (tecnicos != null) {
                        for (Usuario u : tecnicos) {
                %>
                    <option value="<%= u.getIdUsuario() %>"><%= u.getNombres() %> <%= u.getApellidos() %></option>
                <%      }
                    }
                %>
            </select>

            <input type="date" name="fechaVisita" required>
            <input type="text" name="motivo" placeholder="Motivo de la visita" required>
            <textarea name="descripcion" placeholder="Descripción del chequeo realizado" required></textarea>
            <textarea name="observaciones" placeholder="Observaciones adicionales"></textarea>
            <button type="submit">Guardar visita</button>
        </form>

        <h2>Historial de visitas</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>Técnico</th>
                    <th>Motivo</th>
                    <th>Descripción</th>
                    <th>Observaciones</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<VisitaTecnica> visitas = (List<VisitaTecnica>) request.getAttribute("visitas");
                if (visitas != null && !visitas.isEmpty()) {
                    for (VisitaTecnica v : visitas) {
            %>
                <tr>
                    <td><%= v.getIdVisita() %></td>
                    <td><%= v.getFechaVisita() %></td>
                    <td><%= v.getNombreCliente() %></td>
                    <td><%= v.getNombreTecnico() %></td>
                    <td><%= v.getMotivo() %></td>
                    <td><%= v.getDescripcion() %></td>
                    <td><%= v.getObservaciones() %></td>
                    <td><%= v.getEstado() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="8">No hay visitas registradas.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
