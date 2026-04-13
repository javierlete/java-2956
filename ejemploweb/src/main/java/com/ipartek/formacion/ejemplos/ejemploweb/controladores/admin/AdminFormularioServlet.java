package com.ipartek.formacion.ejemplos.ejemploweb.controladores.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona;
import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Rol;

import bibliotecas.fabrica.Fabrica;

@WebServlet("/admin/formulario")
public class AdminFormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición
		String sId = request.getParameter("id");

		// 2. Convertir la información
		Long id = sId != null ? Long.parseLong(sId) : null;

		// 3. Empaquetar en objetos
		// 4. Llamar a la lógica de negocio
		if (id != null) {
			DaoPersona daoPersona = (DaoPersona) Fabrica.getObjeto("dao.persona");
			System.out.println(id);
			Persona persona = daoPersona.obtenerPorIdConRol(id);

			request.setAttribute("persona", persona);
		}

		DaoRol daoRol = (DaoRol) Fabrica.getObjeto("dao.rol");

		Iterable<Rol> roles = daoRol.obtenerTodos();

		// 5. Empaquetar información para la siguiente vista
		request.setAttribute("roles", roles);

		// 6. Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger información de la petición
		String sId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String sFechaNacimiento = request.getParameter("fecha-nacimiento");
		String sRolId = request.getParameter("rol");

		// 2. Convertir la información
		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		LocalDate fechaNacimiento = LocalDate.parse(sFechaNacimiento);
		Long rolId = Long.parseLong(sRolId);

		// 3. Empaquetar en objetos
		Persona persona = new Persona(id, nombre, fechaNacimiento, new Rol(rolId, null, null));

		// 4. Llamar a la lógica de negocio
		DaoPersona daoPersona = (DaoPersona) Fabrica.getObjeto("dao.persona");

		if (id == null) {
			daoPersona.insertar(persona);
		} else {
			daoPersona.modificar(persona);
		}

		// 5. Empaquetar información para la siguiente vista
		// 6. Saltar a la siguiente vista
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}
}
