<%-- 
    Document   : inicio
    Created on : May 11, 2021, 7:32:42 PM
    Author     : Ivanchu
--%>

<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <%
      Usuario user = (Usuario)request.getAttribute("usuario");
    %>
    <body>
        <h1>El Usuario es: <%= user.getNombre() + " " + user.getApellidos() %></h1>
        
        <%
            if (user.getRol().getIdRol() == 1 || user.getRol().getIdRol()== 3)
            {
        %>
        <form action="conversaciones.jsp" >
            <input type="submit" name="Ver Conversaciones">
        </form>
        <%
            }
        %>
        <img src="https://steamuserimages-a.akamaihd.net/ugc/920291347197530171/8E5D94D48CA95EE8CA49BB6752E3C1441802BA3E/">
    </body>
</html>
