<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach items="${productos}" var="p">
			<div class="col">
				<div class="card h-100">
					<jsp:useBean id="ahora" class="java.util.Date" />
					<img src="../imgs/${p.id}.jpg?v=${ahora.time}"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">${p.nombre}</h5>
						<p class="card-text">${p.descripcion}</p>
						<p class="card-text">
							<a class="stretched-link d-block w-100 btn btn-primary"
								href="producto?id=${p.id}"><i class="bi bi-search"></i></a>
						</p>
					</div>
					<div class="card-footer text-end">
						<small class="text-body-secondary"><fmt:formatNumber
								type="currency" value="${p.precio}" /></small>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>