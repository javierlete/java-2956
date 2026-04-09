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

<c:if test="${true}">
	<p>Me veo</p>
</c:if>

<c:if test="${false}">
	<p>No me veo</p>
</c:if>

<c:choose>
	<c:when test="${false}">
		<p>1</p>
	</c:when>
	<c:when test="${true}">
		<p>2</p>
	</c:when>
	<c:when test="${false}">
		<p>3</p>
	</c:when>
	<c:otherwise>
		<p>Default</p>
	</c:otherwise>
</c:choose>

<fmt:formatNumber type="currency" value="1234.567"/>

<jsp:useBean id="ahora" class="java.util.Date" />
<fmt:formatDate dateStyle="long" value="${ahora}"/>

<% request.setAttribute("hoy", java.time.LocalDate.now()); %>
<javatime:format value="${hoy}" style="L-"/>

<%@ include file="/WEB-INF/includes/pie.jsp"%>