package com.ipartek.formacion.ejemplos.amazonia.servicios.impl;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Cliente;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.ClienteRepository;
import com.ipartek.formacion.ejemplos.amazonia.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.amazonia.servicios.ServicioException;
import com.ipartek.formacion.ejemplos.amazonia.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final ClienteRepository clienteRepository;
	
	@Override
	public Cliente registrarDatosCliente(Long idUsuario, Cliente cliente) {
		var usuarioOptional = usuarioRepository.findById(idUsuario);
		
		if(usuarioOptional.isEmpty()) {
			throw new ServicioException("No se ha encontrado el usuario");
		}
		
		var usuario = usuarioOptional.get();
		
		clienteRepository.save(cliente);
		
		usuario.setCliente(cliente);
		
		usuarioRepository.save(usuario);
		
		return cliente; 
	}
	
}
