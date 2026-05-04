package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;;

public class DaoRolJpa implements DaoRol {

	@Override
	public Iterable<Rol> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from Rol", Rol.class).getResultList());
	}

	@Override
	public Rol obtenerPorId(Long id) {
		return ejecutarJpa(em -> em.find(Rol.class, id));
	}

	@Override
	public Rol insertar(Rol rol) {
		return ejecutarJpa(em -> {
			em.persist(rol);
			return rol;
		});
	}

	@Override
	public Rol modificar(Rol rol) {
		return ejecutarJpa(em -> {
			em.merge(rol);
			return rol;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(Rol.class, id));
			return null;
		});
	}
}
