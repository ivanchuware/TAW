<%-- 
    Document   : AdminListar
    Created on : 11-may-2021, 18:33:06
    Author     : aaron
--%>

<%@page import="java.util.List"%>
<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvnTAW - LIstar</title>
    </head>
    <body>
        <table border="1">
        <thead>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>APELLIDOS</th>
            <th>CORREO</th>
            <th>EDITAR USUARIO</th>
            <th>ELIMINAR USUARIO</th>
            </tr>
    <%
        List<Usuario> lista = (List)request.getAttribute("lista");
        for(Usuario u : lista){
            
    %>
              <tr>                  
              <td><%= u.getIdUsuario()  %></td>                  
              <td><%= u.getNombre()  %></td>
              <td><%= u.getApellidos()  %></td>
              <td><%= u.getEmail() %></td> 
              <td><a href="ServletAdminEditarUsuario?idusuario=<%= u.getIdUsuario() %>" >Editar</td>
              <td><a href="ServletAdminEliminarUsuario?idusuario=<%= u.getIdUsuario() %>" >Eliminar</td>
              </tr>
              <% } %>
        </thead>
        </table>
        <a href="AdminAgregarUsuario.jsp">Añadir usuario
    </body>
</html>
