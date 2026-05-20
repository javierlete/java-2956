package com.ipartek.formacion.ejemplos.ipartex.accesodatos.jpa;

import static bibliotecas.accesodatos.DaoJpa.ejecutarJpa;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto;
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
	public Iterable<MensajeListadoDto> obtenerTodosParaListado() {
		return ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto(
				    m.id,
				    m.texto,
				    m.momento,
				    m.usuario.nombre,
				    false,
				    count(mg)
				)
				from Mensaje m
				left join m.meGusta mg
				group by m.id, m.texto, m.momento, m.usuario.nombre
				order by m.momento desc
				""", MensajeListadoDto.class).getResultList());
	}

	@Override
	public Iterable<MensajeListadoDto> obtenerTodosParaListado(Long idUsuario) {
		return ejecutarJpa(em -> em.createQuery("""
				select distinct new com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto(
				    m.id,
				    m.texto,
				    m.momento,
				    m.usuario.nombre,
				    (select max(case when mg.id = :idUsuario then true else false end) from m.meGusta mg),
				    (select count(mg) from m.meGusta mg),
				    (select count(r) from Mensaje r where r.respuestaA = m)
				)
				from Mensaje m
				where m.respuestaA is null
				order by m.momento desc
				""", MensajeListadoDto.class).setParameter("idUsuario", idUsuario).getResultList());
	}

	@Override
	public Iterable<MensajeListadoDto> obtenerRaicesParaListado() {
		return ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto(
				    m.id,
				    m.texto,
				    m.momento,
				    m.usuario.nombre,
				    false,
				    (select count(mg) from m.meGusta mg),
				    (select count(r) from Mensaje r where r.respuestaA = m)
				)
				from Mensaje m
				where m.respuestaA is null
				order by m.momento desc
				""", MensajeListadoDto.class).getResultList());
	}

	@Override
	public Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje) {
		return ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto(
				    m.id,
				    m.texto,
				    m.momento,
				    m.usuario.nombre,
				    false,
				    (select count(mg) from m.meGusta mg),
				    (select count(r) from Mensaje r where r.respuestaA = m)
				)
				from Mensaje m
				where m.respuestaA.id = :idMensaje
				order by m.momento desc
				""", MensajeListadoDto.class).setParameter("idMensaje", idMensaje).getResultList());
	}

	@Override
	public Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje, Long idUsuario) {
		return ejecutarJpa(em -> em.createQuery("""
				select new com.ipartek.formacion.ejemplos.ipartex.dtos.MensajeListadoDto(
				    m.id,
				    m.texto,
				    m.momento,
				    m.usuario.nombre,
				    (select max(case when mg.id = :idUsuario then true else false end) from m.meGusta mg),
				    (select count(mg) from m.meGusta mg),
				    (select count(r) from Mensaje r where r.respuestaA = m)
				)
				from Mensaje m
				where m.respuestaA.id = :idMensaje
				order by m.momento desc
				""", MensajeListadoDto.class).setParameter("idMensaje", idMensaje).setParameter("idUsuario", idUsuario)
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
