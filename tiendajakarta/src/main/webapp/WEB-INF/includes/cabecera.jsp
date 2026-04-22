<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Tienda Jakarta</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="${pageContext.request.contextPath}/cf/">
<link href="../imgs/shop.svg" rel="icon">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-icons.min.css" rel="stylesheet">
<link href="../css/estilos.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark mb-5" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href=""><i class="bi bi-shop"></i> Tienda
				Jakarta</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link" href="">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-sm-0">
					<c:choose>
						<c:when test="${usuario != null}">
							<li class="navbar-text"><i class="bi bi-person"></i>
								${usuario.nombre} (${usuario.rol.nombre})</li>
							<c:if test="${usuario.rol.nombre == 'ADMINISTRADOR'}">
								<li class="nav-item"><a class="nav-link" href="admin/productos">Administración</a></li>
							</c:if>
							<li class="nav-item"><a class="nav-link" href="logout"><i
									class="bi bi-box-arrow-right"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="login"><i
									class="bi bi-box-arrow-in-right"></i></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>