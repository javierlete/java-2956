package com.ipartek.formacion.ejemplos.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.amazonia.config.UsuarioAutenticado;
import com.ipartek.formacion.ejemplos.amazonia.modelos.Carrito;
import com.ipartek.formacion.ejemplos.amazonia.servicios.ClienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@GetMapping("pedido/{id}")
	public String pedido(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("pedido", clienteService.verPedido(id).get());
		
		return "pedido";
	}
	
	@GetMapping("tramitar-pedido")
	public String tramitarPedido(Carrito carrito, UsuarioAutenticado usuarioAutenticado, Model modelo) {
		var pedido = clienteService.tramitarPedido(usuarioAutenticado.getCliente(), carrito);
		
		return "redirect:/cliente/pedido/" + pedido.getId();
	}
	
	@GetMapping("/listar-pedidos")
	public String pedidos(Model modelo, UsuarioAutenticado usuarioAutenticado) {
		modelo.addAttribute("pedidos", clienteService.listarPedidos(null));
		
		return "pedidos";
	}
}
