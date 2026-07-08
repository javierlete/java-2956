package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;

@RepositoryRestResource(path = "clientes", collectionResourceRel = "clientes")
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
