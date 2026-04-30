package com.ipartek.formacion.ejemplos.tiendajakarta.pruebas;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;

import bibliotecas.fabrica.Fabrica;

public class DaoRolJpaPruebas {
	public static void main(String[] args) {
		DaoRol dao = (DaoRol) Fabrica.getObjeto("dao.rol");
		
		dao.insertar(Rol.builder().nombre("Prueba").build());
		dao.insertar(Rol.builder().nombre("Prueba2").build());
		
		for(Rol rol: dao.obtenerTodos()) {
			System.out.println(rol);
		}
	}
}
