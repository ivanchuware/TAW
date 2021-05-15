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
            <input type="submit" value="Añadir nuevo" /><br/>
        </form>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>TÍTULO</th>
                <th>DESCRIPCIÓN</th>
                <th>FECHA</th>
                <th>FECHA LÍMITE DE RESERVA</th>
                <th>PRECIO</th>
                <th>AFORO</th>
                <th>ENTRADAS POR USUSARIO</th>
                <th>ASIENTOS FIJOS ASIGNADOS</th>
                <th>NÚMERO DE FILAS</th>
                <th>ASIENTOS POR FILA</th>
            </tr>
            <%
              for(Evento evento: listaEventos){
            %>
            <tr>
                <td><%=evento.getIdEvento() %> </td>
                <td><%=evento.getTitulo() %> </td>
                <td><%=evento.getDescripcion() %> </td>                
                <td><%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFecha()) %> </td>
                <td><%=new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechares()) %></td>
                <td><%=evento.getCoste() %> </td>
                <td><%=evento.getAforo() %> </td>
                <td><%=evento.getEntradas() %> </td>
                <td><%=evento.getAsientosfijos() %> </td>
                <td><%=evento.getNumfilas() %> </td>
                <td><%=evento.getNumasientosporfila() %> </td>
                <td><a href="ServletEditarEvento?id=<%=evento.getIdEvento() %>">Editar</a></td>
                <td><a href="ServletBorrarEvento?id=<%=evento.getIdEvento() %>">Borrar</a></td>
            </tr>
            <%
               }
                %>
        </table>
        <br/>
        <%
            }}
        %>
    </body>
</html>
