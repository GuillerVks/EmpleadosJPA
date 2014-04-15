<%-- 
    Document   : listado
    Created on : 11-abr-2014, 21:36:38
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            td{
                padding:5px;
                border-bottom:1px solid;
            }
        </style>
    </head>
    <body>
        <table>
             <c:forEach items="${emp}" var="emple" >
            <tr>
                <td>
                    <c:out value="${emple.idEmpleado}" default="Vacio" />
                </td>
                <td>
                    <c:out value="${emple.nombre}" default="Vacio" />
                </td>
                <td>
                    <fmt:formatNumber value="${emple.salario}" type="currency" />
                </td>
                <td>
                    <fmt:formatDate value="${emple.fechaAlta}" />
                </td>
                <td>
                    <form method="POST" action="eliminar.html">
                        <input type="text" name="idDel" value="${emple.idEmpleado}" style="display:none" />
                        <input type="submit" value="Eliminar">
                    </form>
                </td>
                <td>
                    <form method="POST" action="editar.html">
                        <input type="text" name="idMod" value="${emple.idEmpleado}" style="display:none" />
                        <input type="submit" value="Modificar">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>        
    </body>
</html>
