<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main>
	<ul>
		<c:forEach items="${productos}" var="p">
			<li>${p.nombre}: <fmt:formatNumber type="currency" value="${p.precio}"/></li>
		</c:forEach>
	</ul>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>