package com.ipartek.formacion.ejemplos.tiendajakarta.modelos;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {
	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;

	public Producto(Long id, String nombre, String descripcion, BigDecimal precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}

	@Override
	public String toString() {
		return String.format("Producto [id=%s, nombre=%s, descripcion=%s, precio=%s]", id, nombre, descripcion, precio);
	}

}
