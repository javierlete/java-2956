<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach items="${productos}" var="p">
			<div class="col">
				<div class="card h-100">
					<img src="https://picsum.photos/400/300?${p.id}" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">${p.nombre}</h5>
						<p class="card-text">${p.descripcion}</p>
					</div>
					<div class="card-footer text-end">
						<small class="text-body-secondary"><fmt:formatNumber type="currency"
					value="${p.precio}" /></small>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>