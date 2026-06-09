package com.ipartek.formacion.ejemplos.ipartexpring.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;

public interface AnonimoService {
	Iterable<Mensaje> listarMensajes();
	Iterable<MensajeListadoDto> listarMensajesListado();
	Iterable<MensajeListadoDto> listarMensajesListado(Long id);
	Iterable<MensajeListadoDto> listarMensajesRaizListado();
	Iterable<MensajeListadoDto> listarRespuestas(Long idMensaje);
	Iterable<MensajeListadoDto> listarRespuestas(Long idMensaje, Long idUsuario);

	Optional<Usuario> autenticar(Usuario usuario);
}
