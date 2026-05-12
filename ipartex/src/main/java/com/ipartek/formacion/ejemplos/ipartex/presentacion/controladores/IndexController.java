package com.ipartek.formacion.ejemplos.ipartex.presentacion.controladores;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

import bibliotecas.controladorfrontal.ControladorFrontalServlet.Datos;
import bibliotecas.controladorfrontal.Ruta;
import bibliotecas.fabrica.Fabrica;

public class IndexController {
	private static final AnonimoNegocio anonimoNegocio = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");
	private static final UsuarioNegocio usuarioNegocio = (UsuarioNegocio) Fabrica.getObjeto("negocio.usuario");

	static {
		var daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje");
		var daoUsuario = (DaoUsuario) Fabrica.getObjeto("dao.usuario");

		var javier = Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build();
		var pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build();

		daoUsuario.insertar(javier);
		daoUsuario.insertar(pepe);

		var mensaje1 = Mensaje.builder().texto("Prueba inicial").usuario(javier).build();
		var mensaje2 = Mensaje.builder().texto("Claro, como eres el que ha hecho la red").usuario(pepe).build();
		var mensaje3 = Mensaje.builder().texto("Es mi privilegio").usuario(javier).build();

		mensaje1.getMeGusta().add(pepe);
		mensaje2.getMeGusta().add(javier);
		mensaje3.getMeGusta().add(pepe);
		mensaje3.getMeGusta().add(javier);

		daoMensaje.insertar(mensaje1);
		daoMensaje.insertar(mensaje2);
		daoMensaje.insertar(mensaje3);
	}

	@Ruta("/mensajes")
	public static String mensajes(Datos datos) {
		var mensajes = anonimoNegocio.listarMensajes();

		datos.salida().put("mensajes", mensajes);

		return "index";
	}

	@Ruta("/enviar")
	public static String enviar(Datos datos) {
		var texto = datos.entrada().get("texto")[0];

		var usuario = (Usuario) datos.sesion().get("usuario");

		var mensaje = Mensaje.builder().texto(texto).usuario(usuario).build();

		usuarioNegocio.enviarMensaje(mensaje);

		return "redirect:/mensajes";
	}

	@Ruta("/login")
	public static String login(Datos datos) {
		if ("GET".equals(datos.metodo())) {
			return "login";
		}

		var email = datos.entrada().get("email")[0];
		var password = datos.entrada().get("password")[0];

		var usuario = Usuario.builder().email(email).password(password).build();

		var usuarioLogin = anonimoNegocio.autenticar(usuario);

		if (usuarioLogin.isPresent()) {
			datos.sesion().put("usuario", usuarioLogin.get());

			return "redirect:/mensajes";
		} else {
			return "redirect:/login";
		}
	}

	@Ruta("/logout")
	public static String logout(Datos datos) {
		datos.cerrarSesion().set(true);

		return "redirect:/login";
	}

	@Ruta("/me-gusta")
	public static String meGusta(Datos datos) {
		return procesarMeGusta(datos, true);
	}

	@Ruta("/no-me-gusta")
	public static String noMeGusta(Datos datos) {
		return procesarMeGusta(datos, false);
	}

	private static String procesarMeGusta(Datos datos, boolean meGusta) {
		var sIdMensaje = datos.entrada().get("id")[0];

		var idMensaje = Long.parseLong(sIdMensaje);

		var usuario = (Usuario) datos.sesion().get("usuario");

		var idUsuario = usuario.getId();
		
		System.out.printf("Usuario: %s, Mensaje: %s\n", idUsuario, idMensaje);

		if (meGusta) {
			System.out.println("ME GUSTA");
			
			usuarioNegocio.meGusta(idUsuario, idMensaje);
		} else {
			System.out.println("NO ME GUSTA");

			usuarioNegocio.noMeGusta(idUsuario, idMensaje);
		}

		return "redirect:/mensajes";
	}
}
