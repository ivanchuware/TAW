<%-- 
    Document   : conversacion
    Created on : May 13, 2021, 1:53:52 PM
    Author     : Ivanchu
--%>

<%@page import="eventos.entity.Mensaje"%>
<%@page import="eventos.entity.Conversacion"%>
<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conversaciones</title>
    </head>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario");

    %>

    <table border="1">
        <thead>
            <tr>

                <th>Usuario</th>
                <th>Tipo Usuario</th>
                <th>Ultimo mensaje</th>
                <th>Escrito Por</th>
                <th>A las:</th>
            </tr>
        </thead>
        <tbody>
            <%        
                for (Conversacion conversacion : user.getConversacionList1()) {
                String nombrecompleto = conversacion.getIdUsuario2().getNombre() + " " + conversacion.getIdUsuario2().getApellidos();    
            %>            
            <tr>
                <td><a href="ServletConversacion?conversacion=<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
                <td><%=conversacion.getIdUsuario2().getRol().getDescripcion()%></td>
                <%
                    if (conversacion.getMensajeList().isEmpty())
                    {
                        
                %>
                <td>no hay mensajes</td>
                <td></td>
                <td></td>
                <%
                } else {
                Mensaje ultimomensaje = conversacion.getMensajeList().get(conversacion.getMensajeList().size() - 1);
                String horayminuto = ultimomensaje.getHora()+":"+ultimomensaje.getMinuto();
                %>
                <td><%=ultimomensaje.getMensaje() %></td>
                <td><%=ultimomensaje.getIdUsuario().getNombre()%></td>
                <td><%=horayminuto%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
                for (Conversacion conversacion : user.getConversacionList()) {
                String nombrecompleto = conversacion.getIdUsuario1().getNombre() + " " + conversacion.getIdUsuario1().getApellidos();
            %>            
            <tr>
                <td><a href="ServletConversacion?conversacion=<%=conversacion.getIdConversacion()%>"><%=nombrecompleto%></a></td>
                <td><%=conversacion.getIdUsuario1().getRol().getDescripcion()%></td>
                <% if (conversacion.getMensajeList().isEmpty()) {
                %>
                <td>no hay mensajes</td>
                <td></td>
                <td></td>
                <%
                } else {
                Mensaje ultimomensaje = conversacion.getMensajeList().get(conversacion.getMensajeList().size() - 1);
                String horayminuto = ultimomensaje.getHora()+":"+ultimomensaje.getMinuto();
                %>
                <td><%=ultimomensaje.getMensaje() %></td>
                <td><%=ultimomensaje.getIdUsuario().getNombre()%></td>
                <td><%=horayminuto%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>    
        </tbody>
    </table>
    <body>
        
    </body>
</html>
