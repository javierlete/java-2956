package com.ipartek.formacion.ejemplos.tiendajakarta.modelos;

import java.math.BigDecimal;

import bibliotecas.accesodatos.Identificable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto implements Identificable {
	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
}
