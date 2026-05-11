<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">

	<form action="login" method="post">
		<jl:label-input etiqueta="Correo electrónico" id="email" tipo="email" />
		<jl:label-input etiqueta="Contraseña" id="password" tipo="password" />
		
		<jl:label-input etiqueta="Iniciar sesión" tipo="submit" />
	</form>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>