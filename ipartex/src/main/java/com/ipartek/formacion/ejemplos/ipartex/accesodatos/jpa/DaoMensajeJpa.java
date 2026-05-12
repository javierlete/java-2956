package com.ipartek.formacion.ejemplos.ipartex.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;

import bibliotecas.accesodatos.DaoGenericoJpa;;

public class DaoMensajeJpa extends DaoGenericoJpa<Mensaje> implements DaoMensaje {

	public DaoMensajeJpa() {
		super(Mensaje.class);
	}

	@Override
	public Iterable<Mensaje> obtenerTodos() {
		return ejecutarJpa(
				em -> em.createQuery("from Mensaje m outer join fetch m.meGusta order by m.momento desc", Mensaje.class)
						.getResultList());
	}

	@Override
	public void insertarMeGusta(long idUsuario, long idMensaje) {
		ejecutarJpa(em -> em
				.createNativeQuery("INSERT INTO me_gustas (me_gusta_id, mensaje_id) VALUES (:usuario, :mensaje)")
				.setParameter("usuario", idUsuario).setParameter("mensaje", idMensaje).executeUpdate());
	}

	@Override
	public void borrarMeGusta(long idUsuario, long idMensaje) {
		ejecutarJpa(em -> em
				.createNativeQuery("DELETE FROM me_gustas WHERE mensaje_id = :mensaje AND me_gusta_id = :usuario")
				.setParameter("usuario", idUsuario).setParameter("mensaje", idMensaje).executeUpdate());
	}

}
