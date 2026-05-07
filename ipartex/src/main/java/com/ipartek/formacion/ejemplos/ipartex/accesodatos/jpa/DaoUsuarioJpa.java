package com.ipartek.formacion.ejemplos.ipartex.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

import bibliotecas.accesodatos.DaoGenericoJpa;;

public class DaoUsuarioJpa extends DaoGenericoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class);
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return Optional.ofNullable(
				ejecutarJpa(em -> em.createQuery("from Usuario u where u.email=:email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}
}
