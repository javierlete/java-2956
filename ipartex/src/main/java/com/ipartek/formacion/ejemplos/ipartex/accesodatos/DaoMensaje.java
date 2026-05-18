package com.ipartek.formacion.ejemplos.ipartex.accesodatos;

import com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.Dao;

public interface DaoMensaje extends Dao<Mensaje> {
	Iterable<MensajeListadoDto> obtenerTodosParaListado();
	Iterable<MensajeListadoDto> obtenerTodosParaListado(Long idUsuario);

	void insertarMeGusta(long idUsuario, long idMensaje);

	void borrarMeGusta(long idUsuario, long idMensaje);
}
