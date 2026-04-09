<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<form action="insertar" method="post">
	<input name="nombre" placeholder="Nombre">

	<button>Guardar</button>
</form>

<ul>
	<c:forEach items="${personas}" var="p">
		<li>${p.nombre}<a onclick="return estasSeguro('${p.nombre}')"
			href="borrar?id=${p.id}">X</a></li>
	</c:forEach>

</ul>

<%@ include file="/WEB-INF/includes/pie.jsp"%>