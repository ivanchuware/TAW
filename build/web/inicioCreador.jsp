<%-- 
    Document   : inicioCreador
    Created on : 12-may-2021, 18:27:44
    Author     : luilo
--%>

<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
    </head>
    <%
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
    %>
    <body>
        <ul>
            <li><a class="active" href="index.html">Inicio</a></li>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <li style="float:right"><a href="about.asp">Sobre nosotros</a></li>
        </ul>
        <h1>¡Bienvenido, <%=nombreCompleto%>!</h1><br/>
        <div>Estos son tus eventos creados:</div><br/>
        <form action="ServletCrearEvento">
            <input type="submit" value="Añadir nuevo" />
        </form>
    </body>
</html>
