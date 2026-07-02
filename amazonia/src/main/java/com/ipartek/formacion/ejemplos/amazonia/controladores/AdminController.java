package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;
import com.ipartek.formacion.ejemplos.amazonia.servicios.AdministradorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin/productos")
public class AdminController {
	private final AdministradorService administradorService;

	@GetMapping("listado")
	public String listado(Model modelo) {
		modelo.addAttribute("productos", administradorService.listarProductos());

		return "admin/productos/listado";
	}

	@GetMapping("formulario")
	public String formulario(Producto producto) {
		return "admin/productos/formulario";
	}

	@GetMapping("formulario/{id}")
	public String formulario(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("producto", administradorService.obtenerProductoPorId(id).orElse(null));

		return "admin/productos/formulario";
	}

	@GetMapping("borrar/{id}")
	public String borrar(@PathVariable Long id) {
		administradorService.borrarProducto(id);

		return "redirect:/admin/productos/listado";
	}

	@PostMapping("guardar")
	public String guardar(@Valid Producto producto, BindingResult bindingResult, MultipartFile fichero) {
		if(bindingResult.hasErrors()) {
			return "admin/productos/formulario";
		}
		
		Producto productoNuevo = administradorService.guardarProducto(producto);
		
		administradorService.subirFoto(productoNuevo.getId(), fichero);

		return "redirect:/admin/productos/listado";
	}
}
