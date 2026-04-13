package com.ipartek.formacion.ejemplos.ejemploweb.accesodatos;

import com.ipartek.formacion.ejemplos.ejemploweb.modelos.Persona;

import bibliotecas.accesodatos.Dao;
import bibliotecas.accesodatos.DaoException;

public interface DaoPersona extends Dao<Persona> {
	Iterable<Persona> buscarPorNombre(String nombre);
	default Iterable<Persona> obtenerTodosConRol() {
		throw new DaoException("NO IMPLEMENTADO");
	}
	default Persona obtenerPorIdConRol(Long id) {
		throw new DaoException("NO IMPLEMENTADO");
	}
}
