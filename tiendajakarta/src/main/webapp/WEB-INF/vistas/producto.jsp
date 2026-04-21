<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">
	<div class="card mb-3">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="../imgs/${producto.id}.jpg" class="img-fluid rounded-top rounded-md-start" alt="...">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">${producto.nombre}</h5>
					<p class="card-text">${producto.descripcion}</p>
					<p class="card-text">
						<small class="text-body-secondary"><fmt:formatNumber type="currency"
					value="${producto.precio}" /></small>
					</p>
				</div>
			</div>
		</div>
	</div>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>