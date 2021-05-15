<%-- 
    Document   : crearEvento
    Created on : 13-may-2021, 11:57:24
    Author     : luilo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="eventos.entity.Evento"%>
<%@page import="eventos.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Crear evento</title>        
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
        <script type="text/javascript">
    function showContent() {
        element = document.getElementById("content");
        check = document.getElementById("asientosFijos");
        if (check.checked) {
            element.style.display='block';
        }
        else {
            element.style.display='none';
        }
    }
</script>
    </head>
    <%
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        Boolean error = (Boolean)request.getAttribute("error");
        String errorMsg = "";
        if (error == null) {
            error = false;
        }
        if (error) {
            errorMsg = (String)request.getAttribute("errorMsg");
        }
        Evento evento = (Evento) request.getAttribute("evento");
        String strTitulo = "", strDescripcion = "", strPrecio = "",strFecha="", strFechaRes="",
                strAforo = "", strEntradas = "", strAsientosFijos = "", strNumFilas = "", strAsientosFila = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(evento!=null){
            strTitulo = evento.getTitulo();
            strDescripcion = evento.getDescripcion();
            strFecha = df.format(evento.getFecha());
            strFechaRes = df.format(evento.getFechares());
            strPrecio = evento.getCoste().toString();
            strAforo = evento.getAforo().toString();
            strEntradas = evento.getEntradas().toString();
            if(evento.getAsientosfijos()=='S'){
                strAsientosFijos = "checked";
            }
            strNumFilas = evento.getNumfilas().toString();
            strAsientosFila = evento.getNumasientosporfila().toString();
        }
%>
    <body>
        <ul>
            <li><a class="active" href="index.html">Inicio</a></li>
            <li><a href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <li style="float:right"><a href="about.asp">Sobre nosotros</a></li>
        </ul>
        <h1>Rellene los siguientes campos para crear un evento:</h1>
        <form method="get" action="ServletGuardarEvento" accept-charset="UTF-8">
                <div class="block">
                    <label for="titulo">Título: </label><input type="text" name="titulo" maxlength="60" size="60" value="<%=strTitulo%>" />
                </div>
                <div class="block">
                    <label for="descripcion">Descripción: </label><textarea name="descripcion" rows="5" cols="60" ><%=strDescripcion%></textarea>
                </div>
                <div class="block">
                    <label for="fecha">Fecha del evento: </label><input type="date" name="fecha" value="<%=strFecha%>">
                </div>
                <div class="block">
                    <label for="fechaReserva">Fecha límite de reserva de entradas: </label><input type="date" name="fechaReserva" value="<%=strFechaRes%>">
                </div>
                <div class="block">
                    <label for="precio">Precio: </label><input type="text" name="precio"  maxlength="10" size="10" value="<%=strPrecio%>" />
                </div>
                <div class="block">
                    <label for="aforo">Aforo: </label><input type="text" name="aforo"  maxlength="10" size="10" value="<%=strAforo%>" />
                </div>
                <div class="block">
                    <label for="limiteEntradas">Número máximo de entradas por usuario: </label><input type="text" name="limiteEntradas"  maxlength="10" size="10" value="<%=strEntradas%>" />
                </div>
                <div class="block">
                    <label for="asientosFijos">Asientos fijos asignados: </label>
                    <input type="checkbox" name="asientosFijos" id="asientosFijos" value="asientosFijos" onchange="javascript:showContent()" <%=strAsientosFijos%> />
                </div>
                    <%
                        if(strAsientosFijos.length()>0){
                        %>
                <div class="block" id="content" style="display: none;">
                    <label for="numFilas">Número de filas: </label><input type="text" name="numFilas"  maxlength="10" size="10" value="<%=strNumFilas%>" />
                    <label for="asientosFila">Número de asientos por fila: </label><input type="text" name="asientosFila"  maxlength="10" size="10" value="<%=strAsientosFila%>" /></br>
                </div>           
                <%
                    }if (error) {
                %>
                <div class="block">
                    Error procesando datos: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <div class="block">
                    <input type="submit" value="Guardar evento">
                </div>
        </form>
    </body>
</html>
