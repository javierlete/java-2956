package com.ipartek.formacion.ejemplos.ejemploweb.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;

import bibliotecas.fabrica.Fabrica;

@WebServlet("/insertar")
public class InsertarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición
		String nombre = request.getParameter("nombre");

		// 2. Convertir la información
		// 3. Empaquetar en objetos
		Persona persona = new Persona(nombre);

		// 4. Llamar a la lógica de negocio
		DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");

		dao.insertar(persona);

		// 5. Empaquetar información para la siguiente vista
		// 6. Saltar a la siguiente vista
		response.sendRedirect("index");
	}
}
