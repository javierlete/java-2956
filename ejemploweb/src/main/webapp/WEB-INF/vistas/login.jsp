<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main>
	<form action="login" method="post">
		<fieldset>
			<legend>Inicio sesión</legend>

			<div>
				<label for="email">Correo electrónico</label> <input id="email" name="email"
					type="email" placeholder="Correo electrónico">
			</div>

			<div>
				<label for="password">Contraseña</label> <input id="password" name="password"
					type="password" placeholder="Contraseña">
			</div>
			
			<button>Inicio sesión</button>
		</fieldset>
	</form>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>