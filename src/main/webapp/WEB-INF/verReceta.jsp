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
	<div class="nav4">
		<h1 class="nav-title"> ${receta.title} creada por ${receta.usuario.name}</h1>
		<div class="nav-links">
			<a class="link" href="/recetas">Regresar al recetario </a>
			<a class="link" href="/salir">Salir </a>
	 	</div>
	</div>	 	
	<div class="content4">	
		<div class="sec-inst">	
			<h5>Instrucciones:</h5>
			<div class="lista-instr">${receta.instructions}</div>
		</div>
		<div class="sec-ingr">	
			<h5>Lista de ingredientes :</h5>
			<table class="table table-striped">
				<thead>
				<tr>
					<td> Ingrediente </td>
					<td> Cantidad </td>
				</tr>
				</thead>
			<tbody>
				<c:forEach var="ingrediente" items="${ingredientes}">
					<tr>
					<td><c:out value="${ingrediente.nombre}"/> </td>
					<td><c:out value="${ingrediente.cantidad}"/> </td>
					</tr>
				</c:forEach>	
			</tbody>
			</table>
			</div>
		<div class="botones">
			<c:if test="${esCreador}" >
				<a href="/recetas/${receta.id}/ingredientes"><button>Agregar Ingredientes</button></a>
				<a href="/recetas/${receta.id}/actualizar"><button>Editar Receta</button></a>
				<a href="/recetas/${receta.id}/eliminar"> <button>Borrar receta</button> </a>
			</c:if>
		</div>
	</div>

</div>
</body>
</html>

