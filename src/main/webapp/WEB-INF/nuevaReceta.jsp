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
    <title>Nueva Receta</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/inicio.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="nav3">
		<h1 class="nav-title">Agrega una nueva receta</h1>
		<div class="nav-links">
			<a class="link" href="/recetas">Regresar al recetario </a>
			<a class="link" href="/salir">Salir </a>
	 	</div>
	</div>
	<div class="content3">	
		<form:form action="/nuevaReceta" method="post" modelAttribute="receta">
			<div class="renglon">
				<form:label path="title">Nombre de la receta:</form:label>
				<form:input class="nombre" path="title"/>
				<form:errors path="title" class="text-danger"/>
			</div>
			<div class="renglon">
				<form:label path="instructions">Instrucciones:</form:label>
				<form:textarea class="instrucciones"  path="instructions"/>
				<form:errors path="instructions"  class="text-danger"/>
			</div>
			<div>
				<form:input type="hidden" path="usuario" value="${usuario.id}" />
				<form:errors path="usuario"  class="error"/>
			</div>
		<div class="guardar">
				<button >Guardar receta</button>
		</div>
		</form:form>
	</div>
</div>   
</body>
</html>

