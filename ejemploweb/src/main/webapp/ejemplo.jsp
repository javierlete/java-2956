<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo JSP</title>
</head>
<body>
<h1>Ejemplo JSP</h1>

<% for(int i = 1; i <= 5; i++) { %>
	<p><%=i%></p>
<% } %>

</body>
</html>
