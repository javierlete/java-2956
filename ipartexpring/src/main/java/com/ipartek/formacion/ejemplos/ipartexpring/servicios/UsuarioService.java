package com.ipartek.formacion.ejemplos.ipartexpring.servicios;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;

public interface UsuarioService {
	Mensaje enviarMensaje(Mensaje mensaje);

	void meGusta(long idUsuario, long idMensaje);

	void noMeGusta(long idUsuario, long idMensaje);

}
