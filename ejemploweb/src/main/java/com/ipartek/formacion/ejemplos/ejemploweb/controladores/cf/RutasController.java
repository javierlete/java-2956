package com.ipartek.formacion.ejemplos.ejemploweb.controladores.cf;

import java.time.LocalDate;

import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoPersona;
import com.ipartek.formacion.ejemplos.ejemploweb.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.ejemploweb.controladores.ControladorFrontalServlet.Datos;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;
import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Rol;

import bibliotecas.fabrica.Fabrica;

public class RutasController {
	private static final DaoPersona DAO_PERSONA = (DaoPersona) Fabrica.getObjeto("dao.persona");
	private static final DaoRol DAO_ROL = (DaoRol) Fabrica.getObjeto("dao.rol");

	public static String index(Datos datos) {
		datos.salida().put("personas", DAO_PERSONA.obtenerTodos());

		return "index";
	}

	public static String listado(Datos datos) {
		datos.salida().put("personas", DAO_PERSONA.obtenerTodosConRol());

		return "admin/listado";
	}

	public static String formulario(Datos datos) {
		String sId = datos.entrada().get("id") != null ? datos.entrada().get("id")[0] : null;

		if (sId != null) {
			Long id = Long.parseLong(sId);

			datos.salida().put("persona", DAO_PERSONA.obtenerPorIdConRol(id));
		}

		datos.salida().put("roles", DAO_ROL.obtenerTodos());

		return "admin/formulario";
	}

	public static String formularioPost(Datos datos) {
		// 1. Recoger información de la petición
		String sId = datos.entrada().get("id")[0];
		String nombre = datos.entrada().get("nombre")[0];
		String sFechaNacimiento = datos.entrada().get("fecha-nacimiento")[0];
		String sRolId = datos.entrada().get("rol")[0];

		// 2. Convertir la información
		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		LocalDate fechaNacimiento = sFechaNacimiento.isBlank() ? null : LocalDate.parse(sFechaNacimiento);
		Long rolId = sRolId.isBlank() ? null : Long.parseLong(sRolId);

		// 3. Empaquetar en objetos
		Persona persona = new Persona(id, nombre, fechaNacimiento, new Rol(rolId, null, null));

		// 4. Llamar a la lógica de negocio
		if (persona.tieneErrores()) {
			// 5. Empaquetar información para la siguiente vista
			// 6. Saltar a la siguiente vista
			datos.salida().put("persona", persona);
			return "formulario";
		}

		if (id == null) {
			DAO_PERSONA.insertar(persona);
		} else {
			DAO_PERSONA.modificar(persona);
		}

		// 5. Empaquetar información para la siguiente vista
		// 6. Saltar a la siguiente vista
		return "redirect:/admin/listado";
	}
	
	public static String borrar(Datos datos) {
		String sId = datos.entrada().get("id")[0];
		
		Long id = Long.parseLong(sId);
		
		DAO_PERSONA.borrar(id);
		
		return "redirect:/admin/listado";
	}
}
