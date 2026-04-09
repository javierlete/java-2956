<%@page
	import="com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<form action="insertar" method="post">
	<input name="nombre" placeholder="Nombre">

	<button>Guardar</button>
</form>

<ul>
	<%
	@SuppressWarnings("unchecked")
	Iterable<Persona> personas = (Iterable<Persona>) request.getAttribute("personas");

	for (Persona p : personas) {
	%>
	<li><%=p.getNombre()%> <a
		onclick="return estasSeguro('<%=p.getNombre()%>')"
		href="borrar?id=<%=p.getId()%>">X</a></li>
	<%
	}
	%>
</ul>

<%@ include file="/WEB-INF/includes/pie.jsp"%>