<%-- 
    Document   : combopuestos
    Created on : 16-abr-2014, 19:41:30
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select name="Puesto">
    <c:forEach items="${puestos}" var="puesto">
        <option value="${puesto.idPuesto}">${puesto.nombre}</option>
    </c:forEach>
</select>
