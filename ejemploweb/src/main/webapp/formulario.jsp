<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main>
	<form action="listado.jsp">
		<fieldset>
			<legend>Persona</legend>

			<div>
				<label for="nombre">Nombre</label> <input id="nombre" type="text"
					placeholder="Nombre">
			</div>
			
			<div>
				<label for="fecha-nacimiento">Fecha de nacimiento</label> <input
					id="fecha-nacimiento" type="date" placeholder="Fecha de nacimiento">
			</div>
			
			<div>
				<label for="rol">Rol</label> <select id="rol">
					<option>Selecciona un rol</option>
				</select>
			</div>
			
			<button>Guardar</button>
		</fieldset>
	</form>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>