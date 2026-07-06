package com.ipartek.formacion.ejemplos.amazonia;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

import lombok.Getter;
import lombok.ToString;

@SessionScope
@Component
@ToString
public class Carrito {
	@Getter
	private final Set<Producto> productos = new HashSet<>();
}
