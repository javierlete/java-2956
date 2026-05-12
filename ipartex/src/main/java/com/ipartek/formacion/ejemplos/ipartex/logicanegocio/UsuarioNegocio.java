package com.ipartek.formacion.ejemplos.ipartex.logicanegocio;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

public interface UsuarioNegocio {
	Mensaje enviarMensaje(Mensaje mensaje);

	void meGusta(long idUsuario, long idMensaje);

	void noMeGusta(long idUsuario, long idMensaje);

}
