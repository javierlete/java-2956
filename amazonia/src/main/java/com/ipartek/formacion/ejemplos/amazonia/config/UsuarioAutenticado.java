package com.ipartek.formacion.ejemplos.amazonia.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Usuario;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UsuarioAutenticado extends Usuario implements UserDetails {
	private static final long serialVersionUID = 8349849683112152228L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> "ROLE_" + getRol().getNombre());
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

}
