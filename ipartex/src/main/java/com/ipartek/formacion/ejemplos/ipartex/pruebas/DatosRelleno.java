package com.ipartek.formacion.ejemplos.ipartex.pruebas;

import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoMensaje;
import com.ipartek.formacion.ejemplos.ipartex.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Mensaje;
import com.ipartek.formacion.ejemplos.ipartex.entidades.Usuario;

import bibliotecas.fabrica.Fabrica;

public class DatosRelleno {
	public static void rellenar() {
		var daoMensaje = (DaoMensaje) Fabrica.getObjeto("dao.mensaje");
		var daoUsuario = (DaoUsuario) Fabrica.getObjeto("dao.usuario");

		var javier = Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build();
		var pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build();

		daoUsuario.insertar(javier);
		daoUsuario.insertar(pepe);

		var mensaje1 = Mensaje.builder().texto("Prueba inicial").usuario(javier).build();
		var mensaje2 = Mensaje.builder().texto("Claro, como eres el que ha hecho la red").usuario(pepe).build();
		var mensaje3 = Mensaje.builder().texto("Es mi privilegio").usuario(javier).build();

		mensaje1.getMeGusta().add(pepe);
		mensaje2.getMeGusta().add(javier);
		mensaje3.getMeGusta().add(pepe);
		mensaje3.getMeGusta().add(javier);

		daoMensaje.insertar(mensaje1);
		daoMensaje.insertar(mensaje2);
		daoMensaje.insertar(mensaje3);
	}
}
