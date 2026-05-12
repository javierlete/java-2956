package com.ipartek.formacion.ejemplos.ipartex.entidades;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 512)
	private String texto;
	
	@NotNull
	@PastOrPresent
	@Builder.Default
	private LocalDateTime momento = LocalDateTime.now();

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Builder.Default

	@ManyToMany
	@JoinTable(
		    name = "me_gustas",
		    joinColumns = @JoinColumn(name = "mensaje_id"),
		    inverseJoinColumns = @JoinColumn(name = "me_gusta_id")
		)
	private Set<Usuario> meGusta = new HashSet<>();
	
	public int getNumeroMeGusta() {
		return meGusta.size();
	}
	
	public boolean leGustaEsteMensaje(Long id) {
		return meGusta.stream().map(u -> u.getId()).filter(elId -> id == elId).findAny().isPresent();
	}
}
