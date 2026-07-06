package com.ipartek.formacion.ejemplos.amazonia.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.ejemplos.amazonia.Carrito;
import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@ControllerAdvice
public class ControladoresConfig {

	private final AnonimoService anonimoService;
	private final Carrito carrito;
	
	@ModelAttribute("categorias")
    public Iterable<Categoria> categorias() {
        return anonimoService.listarCategorias();
    }
	
	@ModelAttribute("carrito")
	public Carrito carrito() {
		return carrito;
	}

//	@ModelAttribute
//	public void modelo(Model modelo) {
//		modelo.addAttribute("categorias", anonimoService.listarCategorias());
//	}
}
