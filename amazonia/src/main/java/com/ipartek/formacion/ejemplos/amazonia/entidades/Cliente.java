package com.ipartek.formacion.ejemplos.amazonia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50)
	private String razonSocial;
	
	@NotBlank
	@Size(max = 50)
	private String nombre;
	
	@NotBlank
	@Size(max = 100)
	private String apellidos;
	
	@NotBlank
	@Size(min = 9, max = 9)
	@Pattern(regexp = "^[XYZABC\\d]\\d{7}[A-Z\\d]$")
	private String nif;
}
