package com.ipartek.formacion.ejemplos.amazonia.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	private static final BigDecimal IVA = new BigDecimal("0.21");

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Linea> lineas = new ArrayList<>();
	
	public BigDecimal getTotal() {
		return lineas.stream().map(linea -> linea.getTotal()).reduce(BigDecimal.ZERO, (total, acumulado) -> total.add(acumulado));
	}
	
	public BigDecimal getIva() {
		return getTotal().multiply(IVA);
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Builder
	
	@Entity
	@Table(name = "pedido-lineas")
	public static class Linea {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
		private Producto producto;
		
		private Integer cantidad;
		
		public BigDecimal getSubTotal() {
			return producto.getPrecio().multiply(new BigDecimal(cantidad));
		}
		
		public BigDecimal getIva() {
			return getSubTotal().multiply(IVA);
		}
		
		public BigDecimal getTotal() {
			return getSubTotal();
		}
	}
}
