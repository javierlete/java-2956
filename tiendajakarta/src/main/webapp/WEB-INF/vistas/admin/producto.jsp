<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/cabecera.jsp"%>

<main class="container">

	<form action="admin/producto" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${producto.id}">
		
		<jl:label-input etiqueta="Nombre" id="nombre" valor="${producto.nombre}" />
		<jl:label-input etiqueta="Precio" id="precio" tipo="number" valor="${producto.precio}" decimales="2" />
		<jl:label-input etiqueta="Descripción" id="descripcion" tipo="textarea" valor="${producto.descripcion}" />
		<jl:label-input etiqueta="Imagen" id="imagen" tipo="file" tiposFichero=".jpg,.jpeg,image/jpeg" />
		
		<jl:label-input etiqueta="Guardar" tipo="submit" />
	</form>

</main>

<%@ include file="/WEB-INF/includes/pie.jsp"%>