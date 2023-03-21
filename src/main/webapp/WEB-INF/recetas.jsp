<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Recetario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
     <link rel="stylesheet" href="/inicio.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="nav2">
		<h1 class="nav-title">Bienvenid@ <c:out value="${usuario.name}"></c:out> al recetario</h1>
		<div class="nav-links">
			<a class="link" href="/nuevaReceta">Agregar nueva receta </a>
			<a class="link" href="/salir">Salir </a>
	 	</div>
	</div>
	<div class="content2">	
		<h5 class="top"> Todas las recetas:</h5>
		<table class="table table-bordered">
			<thead>
			<tr>
				<td>Nombre </td>
				<td>Creada por </td>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="receta" items="${recetas}">
				<tr>
				<td><a href="/recetas/${receta.id}"><c:out value="${receta.title}"/> </a></td>
				<td><c:out value="${receta.usuario.name}"/> </td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
</div>

</body>
</html>

