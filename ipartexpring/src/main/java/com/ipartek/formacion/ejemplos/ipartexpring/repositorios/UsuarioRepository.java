package com.ipartek.formacion.ejemplos.ipartexpring.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
