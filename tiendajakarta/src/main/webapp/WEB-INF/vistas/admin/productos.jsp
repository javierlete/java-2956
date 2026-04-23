<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">

	<table class="table table-bordered table-striped table-hover">
		<thead class="table-secondary">
			<tr>
				<th class="text-end">Id</th>
				<th>Nombre</th>
				<th class="text-end">Precio</th>
				<th>OPCIONES</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${productos}" var="p">
				<tr>
					<th class="text-end">${p.id}</th>
					<td>${p.nombre}</td>
					<td class="text-end"><fmt:formatNumber
								type="currency" value="${p.precio}" /></td>
					<td><a class="btn btn-sm btn-primary" href="admin/producto?id=${p.id}"> <i
							class="bi bi-pencil-fill"></i>
					</a> <a class="btn btn-sm btn-danger" href="admin/producto-borrar?id=${p.id}"> <i
							class="bi bi-trash-fill"></i>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot class="table-secondary">
			<tr>
				<td colspan="3"></td>
				<td><a class="btn btn-sm btn-primary" href="admin/producto">
						<i class="bi bi-plus-lg"></i>
				</a></td>
			</tr>
		</tfoot>
	</table>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>