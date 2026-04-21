<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">
	<form action="login" method="post">
		<fieldset>
			<legend>Inicio sesión</legend>

			<div class="row mb-3">
				<label for="email" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="Email">
				</div>
			</div>
			<div class="row mb-3">
				<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
				<div class="col-sm">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Contraseña">
				</div>
			</div>

			<div class="row mb-3">
				<div class="offset-sm-2 col-sm">
					<button type="submit" class="btn btn-primary">Inicio
						sesión</button>
				</div>
			</div>
		</fieldset>
	</form>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>