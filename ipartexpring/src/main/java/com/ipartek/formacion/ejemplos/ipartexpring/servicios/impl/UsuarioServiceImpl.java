package com.ipartek.formacion.ejemplos.ipartexpring.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.repositorios.MensajeRepository;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.UsuarioService;

import lombok.extern.java.Log;

@Service
@Log
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	public Mensaje enviarMensaje(Mensaje mensaje) {
		log.info("Se va a enviar el siguiente mensaje: " + mensaje);
		
		return mensajeRepository.save(mensaje);
	}

	@Override
	public void meGusta(long idUsuario, long idMensaje) {
		log.info("ME GUSTA " + idUsuario + "<3" + idMensaje);
		mensajeRepository.insertarMeGusta(idUsuario, idMensaje);
	}

	@Override
	public void noMeGusta(long idUsuario, long idMensaje) {
		log.info("NO ME GUSTA " + idUsuario + "<3" + idMensaje);
		mensajeRepository.borrarMeGusta(idUsuario, idMensaje);
	}

}
