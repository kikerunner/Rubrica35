<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="cargarListadoActores" method="post">

	<input type="submit" value="ver listado">

</form>

	<form action="/insertar" method="post">
		<span>Name:</span> <input type="text" name="name"> <br />
		<span>Año nacimiento:</span> <input type="date" name="anonacimiento"> <br />
		<span>Curso:</span> <select id="curso" name="curso">
			<option value="1">Primero</option>
			<option value="2">Segundo</option>			
		</select> <br />
		<input type="submit" value="InsertarActor">
	</form>


<%

List<Actor> actores = (List<Actor>)request.getAttribute("listAllActores");

 pageContext.setAttribute("actores", actores);

%>

<table border="1">



	<thead>
		<tr>
			<td>Cod Actor</td>
			<td>Nombre Actor</td>
			<td>YearOfBirthDate</td>
			<td>Curso</td>
			<td>Borrar</td>
		</tr>
	</thead>
	<tbody>
	
	

		<c:forEach var="actor" items="${listAllActores}">
			<tr>
				<td><c:out value="${actor.cod}"/> </td>
				<td><c:out value="${actor.name}"/> </td>
				<td><c:out value="${actor.yearofbirthdate}"/> </td>
				<td><c:out value="${actor.curso}"/> </td>
				<td><a href="/borrarActor?cod=${actor.cod}">DELETE</a> </td>
	    	</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>