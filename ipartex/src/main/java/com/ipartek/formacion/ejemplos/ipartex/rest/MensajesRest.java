package com.ipartek.formacion.ejemplos.ipartex.rest;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

import bibliotecas.fabrica.Fabrica;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/mensajes")
public class MensajesRest {
	private final AnonimoNegocio anonimoNegocio = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");
	private final UsuarioNegocio usuarioNegocio = (UsuarioNegocio) Fabrica.getObjeto("negocio.usuario");
	
	@GET
	public Iterable<Mensaje> get() {
		return anonimoNegocio.listarMensajes();
	}
	
	@POST
	public Mensaje post(Mensaje mensaje) {
		System.out.println(mensaje);
		
		return usuarioNegocio.enviarMensaje(mensaje);
	}
}
