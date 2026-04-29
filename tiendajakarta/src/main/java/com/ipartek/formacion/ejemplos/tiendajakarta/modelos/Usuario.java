package com.ipartek.formacion.ejemplos.tiendajakarta.modelos;

import bibliotecas.accesodatos.Identificable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario implements Identificable {
	private Long id;
	private String nombre;
	private String email;
	private String password;
	
	private Rol rol;
}
