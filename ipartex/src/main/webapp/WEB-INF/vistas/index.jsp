<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">

	<ul class="list-group list-group mb-4">
		<c:forEach items="${mensajes}" var="m">
			<li
				class="list-group-item d-flex justify-content-between align-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">${m.usuario.nombre}</div>
					${m.texto}
				</div> <span class="badge text-bg-primary rounded-pill"> <javatime:format
						value="${m.momento}" style="LM" />
			</span>
			</li>
		</c:forEach>
	</ul>

	<form action="enviar" method="post">
		<jl:label-input etiqueta="Mensaje" id="texto" tipo="textarea" />
		<jl:label-input etiqueta="Enviar" tipo="submit" />
	</form>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>