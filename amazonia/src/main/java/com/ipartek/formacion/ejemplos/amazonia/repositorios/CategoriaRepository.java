package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;

@RepositoryRestResource(path = "categorias", collectionResourceRel = "categorias")
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
