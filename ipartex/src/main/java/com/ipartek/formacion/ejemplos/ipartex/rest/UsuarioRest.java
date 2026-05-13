package com.ipartek.formacion.ejemplos.ipartex.rest;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

import bibliotecas.fabrica.Fabrica;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/usuarios")
public class UsuarioRest {
	private final AnonimoNegocio anonimoNegocio = (AnonimoNegocio) Fabrica.getObjeto("negocio.anonimo");
	private final UsuarioNegocio usuarioNegocio = (UsuarioNegocio) Fabrica.getObjeto("negocio.usuario");

	@GET
	@Path("/autenticacion")
	public Usuario get(@QueryParam("email") String email, @QueryParam("password") String password) {
		return anonimoNegocio.autenticar(Usuario.builder().email(email).password(password).build())
				.orElseThrow(() -> new NotAuthorizedException("Usuario no autenticado"));
	}
	
	@GET
	@Path("/me-gusta")
	public void meGusta(@QueryParam("mensajeId") Long mensajeId, @QueryParam("usuarioId") Long usuarioId) {
		usuarioNegocio.meGusta(usuarioId, mensajeId);
	}

	@GET
	@Path("/no-me-gusta")
	public void noMeGusta(@QueryParam("mensajeId") Long mensajeId, @QueryParam("usuarioId") Long usuarioId) {
		usuarioNegocio.noMeGusta(usuarioId, mensajeId);
	}
}
