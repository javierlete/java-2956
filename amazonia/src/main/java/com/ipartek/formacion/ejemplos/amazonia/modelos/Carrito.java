package com.ipartek.formacion.ejemplos.amazonia.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.ejemplos.amazonia.entidades.Producto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@SessionScope
@Component
@ToString
public class Carrito {
	private static final BigDecimal IVA = new BigDecimal("0.21");

	@Getter
	private final List<Linea> lineas = new ArrayList<>();
	
	public BigDecimal getTotal() {
		return lineas.stream().map(linea -> linea.getTotal()).reduce(BigDecimal.ZERO, (total, acumulado) -> total.add(acumulado));
	}
	
	public BigDecimal getIva() {
		return getTotal().multiply(IVA);
	}
	
	@Data
	@Builder
	public static class Linea {
		
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
