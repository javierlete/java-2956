<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main>

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Fecha de nacimiento</th>
				<th>Rol</th>
				<th>OPCIONES</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${personas}" var="p">
				<tr>
					<th>${p.id}</th>
					<td>${p.nombre}</td>
					<td>${p.fechaNacimiento}</td>
					<td>${p.rol.nombre}</td>
					<td><a href="admin/formulario?id=${p.id}">Editar</a> <a
						href="admin/borrar?id=${p.id}">Borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
			<tr>
				<td colspan="4"></td>
				<td><a href="admin/formulario">Añadir</a></td>
			</tr>
		</tfoot>
	</table>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>