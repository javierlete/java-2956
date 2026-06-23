package com.ipartek.formacion.ejemplos.amazonia.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Usuario;

import jakarta.validation.constraints.NotBlank;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(@NotBlank String email);
}	
