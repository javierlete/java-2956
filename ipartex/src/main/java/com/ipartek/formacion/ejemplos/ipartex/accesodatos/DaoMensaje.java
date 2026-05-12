package com.ipartek.formacion.ejemplos.ipartex.accesodatos;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.Dao;

public interface DaoMensaje extends Dao<Mensaje> {

	void insertarMeGusta(long idUsuario, long idMensaje);

	void borrarMeGusta(long idUsuario, long idMensaje);
}
