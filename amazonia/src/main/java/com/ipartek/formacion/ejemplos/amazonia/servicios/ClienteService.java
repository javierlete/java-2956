package com.ipartek.formacion.ejemplos.amazonia.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Pedido;
import com.ipartek.formacion.ejemplos.amazonia.modelos.Carrito;

public interface ClienteService {
	 Pedido tramitarPedido(Cliente cliente, Carrito carrito);
	 Iterable<Pedido> listarPedidos(Long idCliente);
	 Optional<Pedido> verPedido(Long id);
}
