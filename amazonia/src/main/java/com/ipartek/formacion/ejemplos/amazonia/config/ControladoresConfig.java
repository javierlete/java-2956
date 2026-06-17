package com.ipartek.formacion.ejemplos.amazonia.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Categoria;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@ControllerAdvice
public class ControladoresConfig {

	private final AnonimoService anonimoService;
	
	@ModelAttribute("categorias")
    public Iterable<Categoria> categorias() {
        return anonimoService.listarCategorias();
    }

//	@ModelAttribute
//	public void modelo(Model modelo) {
//		modelo.addAttribute("categorias", anonimoService.listarCategorias());
//	}
}
