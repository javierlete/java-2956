package com.ipartek.formacion.ejemplos.tiendajakarta.controladores;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class PublicoController {
	private static final DaoProducto DAO_PRODUCTO = (DaoProducto) Fabrica.getObjeto("dao.producto");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.getObjeto("dao.usuario");

	@Ruta("/")
	public static String listadoProductos(Datos datos) {
		datos.salida().put("productos", DAO_PRODUCTO.obtenerTodos());

		return "listado-productos";
	}

	@Ruta("/producto")
	public static String detalleProducto(Datos datos) {
		String sId = datos.entrada().get("id")[0];

		Long id = Long.parseLong(sId);

		datos.salida().put("producto", DAO_PRODUCTO.obtenerPorId(id));

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
		Optional<Usuario> usuarioLoginOptional = DAO_USUARIO.obtenerPorEmailConRol(email);
		
		if (usuarioLoginOptional.isPresent() && usuarioLoginOptional.get().getPassword().equals(usuario.getPassword())) {
			// 5. Empaquetar información para la siguiente vista
			
			datos.sesion().put("usuario", usuarioLoginOptional.get());
			
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
	
	@Ruta("/logout")
	public static String logout(Datos datos) {
		datos.cerrarSesion().set(true);
		
		return "redirect:/";
	}
}
