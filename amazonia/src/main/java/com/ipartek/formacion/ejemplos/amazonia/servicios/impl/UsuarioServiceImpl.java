package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ClienteRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final ClienteRepository clienteRepository;
	
	@Override
	public Cliente registrarDatosCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
}
