<%-- 
    Document   : alta
    Created on : 14-abr-2014, 21:15:30
    Author     : alumno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="alta.html" method="POST">
            Nombre: <input type="text" name="TxNombre" /><br />
            Salario: <input type="text" name="TxSalario" /><br />
            Puesto: <c:import url="/ServletComboPuesto" /><br />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
