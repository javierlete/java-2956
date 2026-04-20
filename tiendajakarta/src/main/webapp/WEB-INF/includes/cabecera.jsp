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
<link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<header class="text-center p-2 text-bg-dark mb-5">
		<h1>
			Tienda Jakarta <span>Usuario: ${email}</span>
		</h1>
	</header>