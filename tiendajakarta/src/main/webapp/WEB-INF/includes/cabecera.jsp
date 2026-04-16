<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Personas</title>
<base href="${pageContext.request.contextPath}/cf/">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
<script defer src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>

	<header>
		<h1>Administración de personas <span>Usuario: ${email}</span></h1>
	</header>
