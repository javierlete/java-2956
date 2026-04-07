package com.ipartek.formacion.ejemplos.ejemploweb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/entrada")
public class Entrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();

		
		
		String nombre = request.getParameter("nombre");
		
		out.printf("""
				<!DOCTYPE html>
				<html>
				<head>
					<meta charset="UTF-8">
					<title>Saludo</title>
				</head>
				<body>
					<h1>Hola %s</h1>
				</body>
				</html>
				""", nombre);
	}

}
