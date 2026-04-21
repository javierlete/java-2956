package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class PublicoController {
	private static final DaoProducto DAO = (DaoProducto) Fabrica.getObjeto("dao.producto");

	@Ruta("/")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", DAO.obtenerTodos());

		return "listado-productos";
	}

	@Ruta("/producto")
	public static String detalleProducto(Datos datos) {
		String sId = datos.entrada().get("id")[0];

		Long id = Long.parseLong(sId);

		datos.salida().put("producto", DAO.obtenerPorId(id));

		return "producto";
	}

	@Ruta("/login")
	public static String login(Datos datos) {
		if (datos.metodo().equals("GET")) {
			return "login";
		}

		// 1. Recoger información de la petición
		String email = datos.entrada().get("email")[0];
		String password = datos.entrada().get("password")[0];

		// 2. Convertir la información
		// 3. Empaquetar en objetos
		Usuario usuario = new Usuario(null, null, email, password);

		// 4. Llamar a la lógica de negocio
		if ("javier@email.net".equals(usuario.getEmail()) && "javier".equals(usuario.getPassword())) {
			// 5. Empaquetar información para la siguiente vista
			// TODO hacer que esto no sea un simulacro
			usuario.setNombre("Javier");
			
			datos.sesion().put("usuario", usuario);
			
			// 6. Saltar a la siguiente vista
			System.out.println("Usuario logueado");
			
			return "redirect:/";
		} else {
			// 5. Empaquetar información para la siguiente vista
			// 6. Saltar a la siguiente vista
			System.out.println("Usuario NO logueado");
			
			return "login";
		}

	}
}
