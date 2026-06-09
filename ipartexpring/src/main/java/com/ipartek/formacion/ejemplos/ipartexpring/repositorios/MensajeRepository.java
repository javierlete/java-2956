package com.ipartek.formacion.ejemplos.ipartexpring.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;

@RepositoryRestResource(path = "mensajes", collectionResourceRel = "mensajes")
public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	@Query("from Mensaje m outer join fetch m.meGusta order by m.momento desc")
	Iterable<Mensaje> findAll();

	@Query("""
			select new com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto(
			    m.id,
			    m.texto,
			    m.momento,
			    m.usuario.nombre,
			    false,
			    count(mg),
			    (select count(r) from Mensaje r where r.respuestaA = m)
			)
			from Mensaje m
			left join m.meGusta mg
			group by m.id, m.texto, m.momento, m.usuario.nombre
			order by m.momento desc
			""")
	Iterable<MensajeListadoDto> obtenerTodosParaListado();

	@RestResource(path = "obtenerTodosParaListadoParaUsuario")
	@Query("""
			select distinct new com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto(
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
			""")
	Iterable<MensajeListadoDto> obtenerTodosParaListado(Long idUsuario);

	@Query("""
			select new com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto(
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
			""")
	Iterable<MensajeListadoDto> obtenerRaicesParaListado();

	@RestResource(path = "respuestasPorMensaje")
	@Query("""
			select new com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto(
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
			""")
	Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje);

	@RestResource(path = "respuestasPorMensajeYUsuario")
	@Query("""
			select new com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeListadoDto(
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
			""")
	Iterable<MensajeListadoDto> obtenerRespuestas(Long idMensaje, Long idUsuario);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO me_gustas (me_gusta_id, mensaje_id) VALUES (:idUsuario, :idMensaje)")
	void insertarMeGusta(long idUsuario, long idMensaje);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM me_gustas WHERE mensaje_id = :idMensaje AND me_gusta_id = :idUsuario")
	void borrarMeGusta(long idUsuario, long idMensaje);
}
