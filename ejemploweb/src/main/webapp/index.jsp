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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>
</head>
<body>

	<form action="index.jsp" method="post">
		<input name="nombre" placeholder="Nombre">

		<button>Guardar</button>
	</form>

	<ul>
		<%
		for (Persona p : dao.obtenerTodos()) {
		%>
		<li><%=p.getNombre()%> <a href="index.jsp?borrar=<%=p.getId()%>">X</a>
		</li>
		<%
		}
		%>
	</ul>

</body>
</html>