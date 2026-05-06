package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;

import bibliotecas.accesodatos.DaoGenericoJpa;;

public class DaoUsuarioJpa extends DaoGenericoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class);
	}

	@Override
	public Optional<Usuario> obtenerPorEmailConRol(String email) {
		return Optional.ofNullable(
				ejecutarJpa(em -> em.createQuery("from Usuario u join fetch u.rol where u.email=:email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}

	@Override
	public Iterable<Usuario> obtenerPorIdRol(Long idRol) {
		return ejecutarJpa(em -> {
			Rol rol = em.find(Rol.class, idRol);
			rol.getUsuarios().size(); // fuerza la carga
			return rol.getUsuarios();
		});
	}
}
