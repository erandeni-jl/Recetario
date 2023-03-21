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
	<link rel="stylesheet" href="/inicio.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="nav6">
		<h1 class="nav-title">Agregar ingredientes a ${receta.title}</h1>
		<div class="nav-links">
			<a class="link" href="/recetas">Regresar al recetario </a>
			<a class="link" href="/salir">Salir </a>
	 	</div>
	</div>
	<div class="content6">	
		<div class="sec-inst">	
				<h5>Instrucciones:</h5>
				<div class="lista-instr">${receta.instructions}</div>
		</div>
		<div class="sec-ingr">		
			<h5>Lista de ingredientes agregados:</h5>
			<table class="table table-striped">
				<thead>
				<tr>
					<td> Cantidad </td>
					<td> Ingrediente </td>
				</tr>
				</thead>
			<tbody>
				<c:forEach var="ingrediente" items="${ingredientes}">
					<tr>
					<td><c:out value="${ingrediente.cantidad}"/> </td>
					<td><c:out value="${ingrediente.nombre}"/> </td>
					</tr>
				</c:forEach>	
			</tbody>
			</table>
		</div>
		<div class="nuevos-ingr">
		<h5>Agregar nuevo ingrediente:</h5>		
			<form:form action="/recetas/ingrediente" method="post" modelAttribute="ingrediente">
				<div class="renglon">
					<form:label path="cantidad"> Cantidad: </form:label>
					<form:input class="texto" path="cantidad"/>
					<form:errors path="cantidad"  class="text-danger"/>
				</div>
				<div class="renglon">
					<form:label path="nombre">Ingrediente:</form:label>
					<form:input class="texto" path="nombre"/>
					<form:errors path="nombre" class="text-danger"/>
				</div>
				<div class="renglon">
					<form:input type="hidden" path="receta" value="${receta.id}" />
					<form:errors path="receta"  class="error"/>
				</div>
				<div  class="guardar-ing">
					<button>Guardar ingrediente</button>
				</div>
			</form:form>
			<div  class="terminar-ing">
				<a href="/recetas/${receta.id}">Terminar de agregar ingredientes</a>
		   </div>
		</div>
	</div>
</div>
</body>
</html>

