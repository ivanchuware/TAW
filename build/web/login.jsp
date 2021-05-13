<%-- 
    Document   : login.jsp
    Created on : May 11, 2021, 6:24:16 PM
    Author     : Ivanchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EvenTAW - Iniciar Sesión</title>
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="css/estiloregistro.css">
    </head>
    <body>
        <ul>
            <li><a  href="index.html">Inicio</a></li>
            <li><a class="active" href="login.jsp">Identificarse</a></li>
            <li><a href="registro.jsp">Registro</a></li>
            <li style="float:right"><a href="about.asp">Sobre nosotros</a></li>
        </ul>
<<<<<<< Updated upstream
    <h1>Introduce los datos para iniciar sesión</h1>
    <div class="form">
        <form action="ServletLogin">
            <div class="block">
                <label for="Correo">Correo</label><input type="text" id="Correo" name="correo"><br>
            </div>
            <div class="block">
                <label for="Contraseña">Contraseña</label><input type="password" id="Contraseña" name="contrasena"><br>
            </div>
            <input type="submit" name="Iniciar Sesión">
        </form>
    </div>
=======
        <h1>Introduce los datos para iniciar sesión</h1>
        <%
            String email = (String) request.getAttribute("correo");
            String contrasena = (String) request.getAttribute("contrasena");
            Boolean error = (Boolean) request.getAttribute("error");
            String errorMsg = "";

            if (error == null) {
                error = false;
            }
            if (error) {
                errorMsg = (String) request.getAttribute("errorMsg");
            }
            if (email == null) {
                email = "";
            }
            if (contrasena == null) {
                contrasena = "";
            }
        %>
        <div class="form">
            <form action="ServletLogin">
                <div class="block">
                    <label for="Correo">Correo</label><input type="text" id="Correo" name="correo" value="<%=email%>"><br>
                </div>
                <div class="block">
                    <label for="Contraseña">Contraseña</label><input type="password" id="Contraseña" name="contrasena"><br>
                </div>
                <%
                    if (error) {
                %>
                <div class="block">
                    Error: <%=errorMsg%>
                </div>
                <%
                    }
                %>
                <input type="submit" name="Iniciar Sesión">
            </form>
        </div>
>>>>>>> Stashed changes
    </body>
</html>
