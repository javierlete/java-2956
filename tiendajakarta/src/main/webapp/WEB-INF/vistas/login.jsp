<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">
	<form action="login" method="post">
		<fieldset>
			<legend>Inicio sesión</legend>

			<jl:label-input etiqueta="Email" id="email" tipo="email" />
			<jl:label-input etiqueta="Contraseña" id="password" tipo="password" />

			<jl:label-input etiqueta="Inicio sesión" tipo="submit"/>
		</fieldset>
	</form>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>