package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.amazonia.dtos.ProductoDto;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

@RepositoryRestResource(path = "productos", collectionResourceRel = "productos")
public interface ProductoRepository extends CrudRepository<Producto, Long> {

	Iterable<Producto> findByNombreContains(String texto);

	@Query("select new com.ipartek.formacion.ejemplos.amazonia.dtos.ProductoDto(p.id, p.nombre, p.descripcion, p.precio) from Producto p where p.categoria.id = :id")
	Iterable<ProductoDto> findByCategoriaId(Long id);
}
