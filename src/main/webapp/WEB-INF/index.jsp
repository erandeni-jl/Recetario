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
    <title>Read Share</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/inicio.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body >
	<div class="container">
		<div class="nav">
			<h1> Recetario!</h1>
			<div>Lugar para almacenar y accesar a todas esas recetas que no estan escritas y no sabemos o a veces olvidamos :) </div>
		</div>
		<div class="content">
			<div class="registro">
				<div class="top">
					<h2> Registro</h2>
				</div>
				<div class="info">
					<form:form action="/crear" method="post" modelAttribute="usuario">
							<div class="renglon">
								<form:label path="name">Nombre:</form:label>
								<form:input class="texto" path="name"/>
								<form:errors path="name" class="text-danger"/>
							</div>
							<div class="renglon"> 
								<form:label path="email">Email:</form:label>
								<form:input class="texto" path="email"/>
								<form:errors path="email" class="text-danger"/>
							</div>
							<div class="renglon">
								<form:label path="password"> Contraseña:</form:label>
								<form:input class="texto" path="password" type="password"/>
								<form:errors path="password" class="text-danger"/>
							</div>
							<div class="renglon">
		 						<form:label path="confirmPassword">Confirma Contraseña:</form:label>
								<form:input class="texto" path="confirmPassword" type="password"/>
								<form:errors path="confirmPassword" class="text-danger"/>
							</div> 
							<button>Crear</button>
					</form:form>
				</div>
			</div>
			<div class="acceso">
				<div class="top">
					<h2>Acceso</h2>
				</div>
				<div class="info">
					<form:form action="/login" method="post" modelAttribute="loginUsuario">
						<div class="renglon">
							<form:label path="email">Email:</form:label>
							<form:input class="texto" path="email"/>
							<form:errors path="email" class="text-danger"/>
						</div>
						<div class="renglon">
							<form:label path="password">Contraseña:</form:label>
							<form:input  class="texto" path="password" type="password"/>
							<form:errors path="password" class="text-danger"/>
						</div>
						<button>Entrar</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

