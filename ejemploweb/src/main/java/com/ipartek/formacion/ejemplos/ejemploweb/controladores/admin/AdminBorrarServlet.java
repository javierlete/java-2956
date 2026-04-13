package com.ipartek.formacion.ejemplos.ejemploweb.controladores.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona;
import bibliotecas.fabrica.Fabrica;

@WebServlet("/admin/borrar")
public class AdminBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Recoger información de la petición
		String sId = request.getParameter("id");
		
		// 2. Convertir la información
		Long id = Long.parseLong(sId);
		
		// 3. Empaquetar en objetos
		// 4. Llamar a la lógica de negocio
		DaoPersona dao = (DaoPersona) Fabrica.getObjeto("dao.persona");
		
		dao.borrar(id);
		
		// 5. Empaquetar información para la siguiente vista
		// 6. Saltar a la siguiente vista
		response.sendRedirect("/ejemploweb/admin/listado");
	}
}
