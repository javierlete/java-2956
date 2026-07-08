package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Pedido;
import com.ipartek.formacion.ejemplos.amazonia.modelos.Carrito;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.ClienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ClienteServiceImpl implements ClienteService {
	private final PedidoRepository pedidoRepository;
	
	@Override
	public Pedido tramitarPedido(Cliente cliente, Carrito carrito) {
		var lineas = carrito.getLineas().stream()
				.map(lc -> Pedido.Linea.builder().producto(lc.getProducto()).cantidad(lc.getCantidad()).build())
				.toList();
		
		var pedido = Pedido.builder().cliente(cliente).lineas(lineas).build();
		
		pedidoRepository.save(pedido);
		
		return pedido;
	}

	@Override
	public Iterable<Pedido> listarPedidos(Long idCliente) {
		return pedidoRepository.findByClienteId(idCliente);
	}

	@Override
	public Optional<Pedido> verPedido(Long id) {
		return pedidoRepository.findById(id);
	}

}
