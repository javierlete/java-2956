package com.ipartek.formacion.ejemplos.ipartex.accesodatos;

import com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.Dao;

public interface DaoMensaje extends Dao<Mensaje> {
	Iterable<MensajeListadoDto> obtenerTodosParaListado();
	Iterable<MensajeListadoDto> obtenerTodosParaListado(Long idUsuario);
	
	Iterable<MensajeListadoDto> obtenerRaicesParaListado();
	Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje);
	Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje, Long idUsuario);

	void insertarMeGusta(long idUsuario, long idMensaje);

	void borrarMeGusta(long idUsuario, long idMensaje);
}
