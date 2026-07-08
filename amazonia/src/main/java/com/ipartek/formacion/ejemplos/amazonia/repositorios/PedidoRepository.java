package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	
	Iterable<Pedido> findByClienteId(Long idCliente);
}
