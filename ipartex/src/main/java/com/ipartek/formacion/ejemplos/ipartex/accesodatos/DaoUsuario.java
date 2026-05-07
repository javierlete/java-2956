package com.ipartek.formacion.ejemplos.ipartex.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.Dao;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmail(String email);
}
