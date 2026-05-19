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
		
		var mensaje4 = Mensaje.builder().texto("Me gusta el mensaje 3").usuario(pepe).build();
		var mensaje5 = Mensaje.builder().texto("Me gusta el mensaje 4").usuario(javier).build();
		var mensaje6 = Mensaje.builder().texto("Me gusto a mi mismo").usuario(javier).build();

		daoMensaje.insertar(mensaje1);
		daoMensaje.insertar(mensaje2);
		daoMensaje.insertar(mensaje3);

		mensaje4.setRespuestaA(mensaje3);
		mensaje5.setRespuestaA(mensaje4);
		mensaje6.setRespuestaA(mensaje3);

		daoMensaje.insertar(mensaje4);
		daoMensaje.insertar(mensaje5);
		daoMensaje.insertar(mensaje6);
	}
}
