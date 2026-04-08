package com.ipartek.formacion.ejemplos.ejemploweb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;

import bibliotecas.fabrica.Fabrica;

@WebServlet("/persona")
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String nombre = request.getParameter("nombre");
		Persona persona = new Persona(nombre);
		
		DaoPersona daoPersona = (DaoPersona) Fabrica.getObjeto("dao.persona");

		daoPersona.insertar(persona);
		
		PrintWriter out = response.getWriter();

		out.append("""
				<meta charset="UTF-8">
				<h1>Listado de personas</h1>
				""");

		
		out.println("<p>Se ha insertado la persona: " + persona.getNombre() + "</p>");

	}

}
