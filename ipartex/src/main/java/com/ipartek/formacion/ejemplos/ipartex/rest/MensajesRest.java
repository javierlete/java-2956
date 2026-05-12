package com.ipartek.formacion.ejemplos.ipartex.rest;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.fabrica.Fabrica;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/mensajes")
public class MensajesRest {
	private final AnonimoNegocio anonimoNegocio = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");

	@GET
	public Iterable<Mensaje> get() {
		return anonimoNegocio.listarMensajes();
	}
}
