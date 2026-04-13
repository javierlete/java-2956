<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp" %>

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
		<tr>
			<th>1</th>
			<td>Javier</td>
			<td>10/11/2003</td>
			<td>Administrador</td>
			<td>
				<a href="formulario.jsp">Editar</a>
				<a href="#">Borrar</a>
			</td>
		</tr>
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="4"></td>
			<td>
				<a href="formulario.jsp">Añadir</a>
			</td>
		</tr>
	</tfoot>
</table>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp" %>