package com.ipartek.formacion.ejemplos.tiendajakarta.modelos;

import bibliotecas.accesodatos.Identificable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "usuarios")
public class Usuario implements Identificable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 20)
	private String nombre;
	
	@NotBlank
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Size(max = 100)
	private String password;
	
	@NotNull
	@ManyToOne
	private Rol rol;
}
