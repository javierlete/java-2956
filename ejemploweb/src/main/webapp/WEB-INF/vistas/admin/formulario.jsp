<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main>
	<form action="admin/formulario" method="post">
		<fieldset>
			<input type="hidden" name="id" value="${persona.id}">
			
			<legend>Persona</legend>

			<div>
				<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
					type="text" placeholder="Nombre" value="${persona.nombre}">
			</div>

			<div>
				<label for="fecha-nacimiento">Fecha de nacimiento</label> <input
					id="fecha-nacimiento" name="fecha-nacimiento" type="date"
					placeholder="Fecha de nacimiento"
					value="${persona.fechaNacimiento}">
			</div>

			<div>
				<label for="rol">Rol</label> <select id="rol" name="rol">
					<option>Selecciona un rol</option>

					<c:forEach items="${roles}" var="r">
						<option value="${r.id}"
							${r.id == persona.rol.id ? 'selected' : ''}>${r.nombre}</option>
					</c:forEach>
				</select>
			</div>

			<button>Guardar</button>
		</fieldset>
	</form>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>