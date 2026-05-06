package com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.jpa;

import com.ipartek.formacion.ejemplos.tiendajakarta.accesodatos.DaoProducto;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.accesodatos.DaoGenericoJpa;;

public class DaoProductoJpa extends DaoGenericoJpa<Producto> implements DaoProducto {

	public DaoProductoJpa() {
		super(Producto.class);
	}
	
}
