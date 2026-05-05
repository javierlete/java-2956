package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.accesodatos.Dao;
import bibliotecas.accesodatos.DaoException;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmailConRol(String email);
	default Iterable<Usuario> obtenerPorIdRol(Long idRol) {
		throw new DaoException("NO IMPLEMENTADO");
	}
}
