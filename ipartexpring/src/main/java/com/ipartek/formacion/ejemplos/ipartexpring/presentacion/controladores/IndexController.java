package com.ipartek.formacion.ejemplos.ipartexpring.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartek.formacion.ejemplos.ipartexpring.dtos.MensajeFormDto;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartexpring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.AnonimoService;
import com.ipartek.formacion.ejemplos.ipartexpring.servicios.UsuarioService;

import jakarta.validation.Valid;

// TODO: Cambiar por sesión cuando la tengamos disponible

@Controller
public class IndexController {
	private static final Usuario USUARIO_PRUEBAS = Usuario.builder().id(1L).email("javier@email.net").nombre("Javier")
			.password("javier").build();

	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("mensajes")
	public String mensajes(Model modelo) {
		var mensajes = anonimoService.listarMensajesListado(USUARIO_PRUEBAS.getId());

		modelo.addAttribute("mensajes", mensajes);
		modelo.addAttribute("mensajeFormDto", new MensajeFormDto(""));

		return "index";
	}

	@PostMapping("enviar")
	public String enviar(@Valid MensajeFormDto mensajeFormDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "index";
		}
		
//		 var usuario = (Usuario) datos.sesion().get("usuario");
		var usuario = USUARIO_PRUEBAS;

		var mensaje = Mensaje.builder().texto(mensajeFormDto.texto()).usuario(usuario).build();

		usuarioService.enviarMensaje(mensaje);

		return "redirect:/mensajes";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@PostMapping("login")
	public String loginPost(Usuario usuario) {
		var usuarioLogin = anonimoService.autenticar(usuario);

		if (usuarioLogin.isPresent()) {
//			datos.sesion().put("usuario", usuarioLogin.get());

			return "redirect:/mensajes";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("logout")
	public String logout() {
//		datos.cerrarSesion().set(true);

		return "redirect:/login";
	}

	@GetMapping("me-gusta")
	public String meGusta(Long idMensaje) {
		return procesarMeGusta(idMensaje, true);
	}

	@GetMapping("no-me-gusta")
	public String noMeGusta(Long idMensaje) {
		return procesarMeGusta(idMensaje, false);
	}

	private String procesarMeGusta(Long idMensaje, boolean meGusta) {
//		var usuario = (Usuario) datos.sesion().get("usuario");
		var usuario = USUARIO_PRUEBAS;
		
		var idUsuario = usuario.getId();

		System.out.printf("Usuario: %s, Mensaje: %s\n", idUsuario, idMensaje);

		if (meGusta) {
			System.out.println("ME GUSTA");

			usuarioService.meGusta(idUsuario, idMensaje);
		} else {
			System.out.println("NO ME GUSTA");

			usuarioService.noMeGusta(idUsuario, idMensaje);
		}

		return "redirect:/mensajes";
	}
}
