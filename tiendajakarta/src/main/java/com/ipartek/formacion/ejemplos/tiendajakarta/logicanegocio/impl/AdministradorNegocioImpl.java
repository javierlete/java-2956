package com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import com.ipartek.formacion.ejemplos.tiendajakarta.logicanegocio.AdministradorNegocio;
import com.ipartek.formacion.ejemplos.tiendajakarta.modelos.Producto;

import bibliotecas.logicanegocio.LogicaNegocioException;
import lombok.extern.java.Log;

@Log
public class AdministradorNegocioImpl extends PublicoNegocioImpl implements AdministradorNegocio {

	@Override
	public void guardarImagenProducto(Long id, String rutaRaiz, InputStream imagen) {
		String ruta = rutaRaiz + "imgs" + File.separator + id + ".jpg";
		
		log.info(ruta);
		
		try {
			imagen.transferTo(new FileOutputStream(ruta));
		} catch (IOException e) {
			log.log(Level.SEVERE, "ERROR AL GUARDAR LA IMAGEN " + ruta, e);
			
			throw new LogicaNegocioException("No se ha podido guardar la imagen " + ruta, e);
		}
	}

	@Override
	public Producto anyadirProducto(Producto producto) {
		return daoProducto.insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		log.info("Se va a modificar el producto " + producto.toString());
		return daoProducto.modificar(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		log.info("Se va a borrar el producto " + id);
		daoProducto.borrar(id);
	}

}
