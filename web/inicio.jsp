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
        <title>EvenTAW - Inicio</title>        
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
    </head>
    <%
      
      Usuario user = (Usuario)session.getAttribute("usuario");
      String nombreCompleto = user.getNombre() + " " + user.getApellidos();
    %>
    <body>
        <ul>
            <li><a class="active" href="index.html">Inicio</a></li>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <li style="float:right"><a href="about.asp">Sobre nosotros</a></li>
        </ul>
        <h1>¡Bienvenido, <%=nombreCompleto%>!</h1><br/>
        
        <%
            if (user.getRol().getIdRol() == 1 || user.getRol().getIdRol()== 3 || user.getRol().getIdRol() == 4)
            {
        %>
        <form action="ServletConversaciones">
            <input type="submit" value="Ver Conversaciones">
        </form>
        <img src="https://steamuserimages-a.akamaihd.net/ugc/920291347197530171/8E5D94D48CA95EE8CA49BB6752E3C1441802BA3E/">
        <%
            }
            if (user.getRol().getIdRol() == 1){
        %>
        <div>Estos son tus eventos creados:</div><br/>
        <form action="ServletCrearEvento">
            <input type="submit" value="Añadir nuevo" />
        </form>
        <%
            }
        %>
    </body>
</html>
