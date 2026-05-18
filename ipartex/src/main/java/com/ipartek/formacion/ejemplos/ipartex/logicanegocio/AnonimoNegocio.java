package com.ipartek.formacion.ejemplos.ipartex.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

public interface AnonimoNegocio {
	Iterable<Mensaje> listarMensajes();
	Iterable<MensajeListadoDto> listarMensajesListado();

	Optional<Usuario> autenticar(Usuario usuario);

}
