package com.ipartek.formacion.ejemplos.ipartex.logicanegocio.impl;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.UsuarioNegocio;

import bibliotecas.fabrica.Fabrica;
import lombok.extern.java.Log;

@Log
public class UsuarioNegocioImpl implements UsuarioNegocio {
	private final DaoMensaje daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje");
	
	@Override
	public Mensaje enviarMensaje(Mensaje mensaje) {
		log.info("Se va a enviar el siguiente mensaje: " + mensaje);
		
		return daoMensaje.insertar(mensaje);
	}

	@Override
	public void meGusta(long idUsuario, long idMensaje) {
		log.info("ME GUSTA " + idUsuario + "<3" + idMensaje);
		daoMensaje.insertarMeGusta(idUsuario, idMensaje);
	}

	@Override
	public void noMeGusta(long idUsuario, long idMensaje) {
		log.info("NO ME GUSTA " + idUsuario + "<3" + idMensaje);
		daoMensaje.borrarMeGusta(idUsuario, idMensaje);
	}

}
