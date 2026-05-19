package com.ipartek.formacion.ejemplos.ipartex.pruebas;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/relleno")
public class DatosRellenoRest {
	@GET
	public void rellenar() {
		DatosRelleno.rellenar();
	}
}
