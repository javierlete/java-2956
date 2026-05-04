package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import java.util.function.Function;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;

import bibliotecas.accesodatos.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoRolJpa implements DaoRol {
	private final static EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.tiendajakarta.modelos");

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

	public static <T> T ejecutarJpa(Function<EntityManager, T> funcion) {
		EntityTransaction t = null;
		EntityManager em = null;

		try {
			em = EMF.createEntityManager();
			t = em.getTransaction();

			t.begin();

			T respuesta = funcion.apply(em);

			t.commit();

			return respuesta;
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}

			throw new DaoException("Error en la operación JPA", e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

}
