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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Recoger información de la petición
		// 2. Convertir la información
		// 3. Empaquetar en objetos
		// 4. Llamar a la lógica de negocio
		DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");
		
		Iterable<Persona> personas = dao.obtenerTodos();
		
		// 5. Empaquetar información para la siguiente vista
		request.setAttribute("personas", personas);
		
		// 6. Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
	}
}
