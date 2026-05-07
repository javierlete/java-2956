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
		
		daoMensaje.insertar(Mensaje.builder().texto("Prueba inicial").usuario(javier).build());
		daoMensaje.insertar(Mensaje.builder().texto("Claro, como eres el que ha hecho la red").usuario(pepe).build());
		daoMensaje.insertar(Mensaje.builder().texto("Es mi privilegio").usuario(javier).build());
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
		
		var mensaje = Mensaje.builder().texto(texto).usuario(Usuario.builder().id(1L).build()).build();
		
		usuarioNegocio.enviarMensaje(mensaje);
		
		return "redirect:/mensajes";
	}
}
