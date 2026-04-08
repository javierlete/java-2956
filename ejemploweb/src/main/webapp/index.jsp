<%@page
	import="com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@page
	import="com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>
</head>
<body>

	<ul>
		<%
		for (Persona p : dao.obtenerTodos()) {
		%>
		<li>
			<%=p.getNombre()%>
		</li>
		<%
		}
		%>
	</ul>

</body>
</html>