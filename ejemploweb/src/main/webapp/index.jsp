<%@page
	import="com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@page
	import="com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");

String nombre = request.getParameter("nombre");
String borrar = request.getParameter("borrar");

if (borrar != null) {
	dao.borrar(Long.parseLong(borrar));
}

if (nombre != null) {
	dao.insertar(new Persona(nombre));
}
%>
<%@ include file="/WEB-INF/includes/cabecera.jsp" %>

	<form action="index.jsp" method="post">
		<input name="nombre" placeholder="Nombre">

		<button>Guardar</button>
	</form>

	<ul>
		<%
		for (Persona p : dao.obtenerTodos()) {
		%>
		<li><%=p.getNombre()%> <a
			onclick="return estasSeguro('<%=p.getNombre()%>')"
			href="index.jsp?borrar=<%=p.getId()%>">X</a></li>
		<%
		}
		%>
	</ul>

<%@ include file="/WEB-INF/includes/pie.jsp" %>