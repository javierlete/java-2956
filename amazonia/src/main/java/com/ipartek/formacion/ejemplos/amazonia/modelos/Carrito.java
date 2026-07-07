package com.ipartek.formacion.ejemplos.amazonia.modelos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@SessionScope
@Component
@ToString
public class Carrito {
	@Getter
	private final List<Linea> lineas = new ArrayList<>();
	
	@Data
	@Builder
	public static class Linea {
		private Producto producto;
		private Integer cantidad;
	}
}
