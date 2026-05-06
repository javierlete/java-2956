package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.PublicoNegocio;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class PublicoController {
	private static final PublicoNegocio negocio = (PublicoNegocio)Fabrica.getObjeto("negocio.publico");
	
	@Ruta("/")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", negocio.listadoProductos());

		return "listado-productos";
	}

	@Ruta("/producto")
	public static String detalleProducto(Datos datos) {
		String sId = datos.entrada().get("id")[0];

		Long id = Long.parseLong(sId);

		datos.salida().put("producto", negocio.detalleProducto(id));

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
		Usuario usuario = Usuario.builder().email(email).password(password).build();

		// 4. Llamar a la lógica de negocio
		Optional<Usuario> usuarioLogin = negocio.autenticar(usuario);
		
		if (usuarioLogin.isPresent()) {
			// 5. Empaquetar información para la siguiente vista
			
			datos.sesion().put("usuario", usuarioLogin.get());
			
			// 6. Saltar a la siguiente vista
			return "redirect:/";
		} else {
			// 5. Empaquetar información para la siguiente vista
			// 6. Saltar a la siguiente vista
			
			return "login";
		}

	}
	
	@Ruta("/logout")
	public static String logout(Datos datos) {
		datos.cerrarSesion().set(true);
		
		return "redirect:/";
	}
}
