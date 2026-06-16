package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

@RepositoryRestResource(path = "productos", collectionResourceRel = "productos")
public interface ProductoRepository extends CrudRepository<Producto, Long> {

	Iterable<Producto> findByNombreContains(String texto);

}
