package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import java.util.function.Function;

import bibliotecas.accesodatos.DaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	private final static EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.tiendajakarta.modelos");
	
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
