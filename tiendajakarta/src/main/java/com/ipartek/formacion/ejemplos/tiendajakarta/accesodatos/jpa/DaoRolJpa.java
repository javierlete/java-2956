package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import java.util.List;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoRolJpa implements DaoRol {
	private final static EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.tiendajakarta.modelos");

	@Override
	public Iterable<Rol> obtenerTodos() {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		List<Rol> roles = em.createQuery("from Rol", Rol.class).getResultList();

		t.commit();

		return roles;
	}

	@Override
	public Rol obtenerPorId(Long id) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Rol rol = em.find(Rol.class, id);

		t.commit();

		return rol;
	}

	@Override
	public Rol insertar(Rol rol) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(rol);

		t.commit();

		return rol;
	}

	@Override
	public Rol modificar(Rol rol) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.merge(rol);

		t.commit();

		return rol;
	}

	@Override
	public void borrar(Long id) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();

		em.remove(em.find(Rol.class, id));
		
		t.commit();
	}

}
