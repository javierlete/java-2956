package com.ipartek.formacion.ejemplos.ipartex.logicanegocio.impl;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartex.logicanegocio.AnonimoNegocio;

import bibliotecas.fabrica.Fabrica;
import lombok.extern.java.Log;

@Log
public class AnonimoNegocioImpl implements AnonimoNegocio {
	private final DaoMensaje daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje");
	private final DaoUsuario daoUsuario = (DaoUsuario) Fabrica.getObjeto("dao.usuario");
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		log.info("Listado de mensajes"); 
		
		return daoMensaje.obtenerTodos();
	}

	@Override
	public Optional<Usuario> autenticar(Usuario usuario) {
		log.info("Intento de autenticación de " + usuario);
		
		var usuarioLogin = daoUsuario.obtenerPorEmail(usuario.getEmail());
		
		if(usuarioLogin.isEmpty() || !usuarioLogin.get().getPassword().equals(usuario.getPassword())) {
			log.warning("Usuario incorrecto");
			
			return Optional.empty();
		}
		
		return usuarioLogin;
	}

}
