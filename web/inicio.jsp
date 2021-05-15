<%-- 
    Document   : inicio
    Created on : May 11, 2021, 7:32:42 PM
    Author     : Ivanchu
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="eventos.entity.Evento"%>
<%@page import="java.util.List"%>
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
    %>
    <body>
        <ul>
            <li><a class="active" href="inicio.jsp">Inicio</a></li>
            <% if (user == null) { %>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <% } %>
            <% if (user != null) { %>
            <li><a href="menuConversaciones.jsp">Ticket de ayuda</a></li>
            <li style="float:right"><a href="about.asp">Mi cuenta</a></li>
            <% } %>
        </ul>
        <%
            if (user == null) { 
        %>
         <h1>Entra con tu cuenta para poder ver los eventos.</h1>
        <%
            } else {
                String nombreCompleto = user.getNombre() + " " + user.getApellidos();
                List<Evento> listaEventos = (List)session.getAttribute("listaEventos");
        %>
        <h1>¡Bienvenido, <%=nombreCompleto%>!</h1><br/>
        <%
            }
        %>

    </body>
</html>
