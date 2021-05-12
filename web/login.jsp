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
    </body>
</html>
