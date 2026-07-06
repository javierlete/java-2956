package com.ipartek.formacion.ejemplos.amazonia;

import java.util.ArrayList;
import java.util.List;

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
	private final List<Producto> productos = new ArrayList<>();
}
