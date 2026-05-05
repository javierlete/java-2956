package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Usuario;;

public class DaoUsuarioJpa implements DaoUsuario {

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return ejecutarJpa(em -> em.find(Usuario.class, id));
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return ejecutarJpa(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return ejecutarJpa(em -> {
			em.merge(usuario);
			return usuario;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(Usuario.class, id));
			return null;
		});
	}

	@Override
	public Optional<Usuario> obtenerPorEmailConRol(String email) {
		return Optional.ofNullable(
				ejecutarJpa(em -> em.createQuery("from Usuario u join fetch u.rol where u.email=:email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}
}
