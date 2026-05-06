package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoRol;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Rol;

import bibliotecas.accesodatos.DaoGenericoJpa;;

public class DaoRolJpa extends DaoGenericoJpa<Rol> implements DaoRol {

	public DaoRolJpa() {
		super(Rol.class);
	}
	
}
