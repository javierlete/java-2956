package pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class TrabajadorPorHoras extends Trabajador {
	private BigDecimal precioHora;
	private Integer numeroHorasMensuales;

	public TrabajadorPorHoras(Long id, String nombre, LocalDate fechaNacimiento, String dni, BigDecimal precioHora,
			Integer numeroHorasMensuales) {
		super(id, nombre, fechaNacimiento, dni);
		this.precioHora = precioHora;
		this.numeroHorasMensuales = numeroHorasMensuales;
	}

	public BigDecimal getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
	}

	public Integer getNumeroHorasMensuales() {
		return numeroHorasMensuales;
	}

	public void setNumeroHorasMensuales(Integer numeroHorasMensuales) {
		this.numeroHorasMensuales = numeroHorasMensuales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numeroHorasMensuales, precioHora);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrabajadorPorHoras other = (TrabajadorPorHoras) obj;
		return Objects.equals(numeroHorasMensuales, other.numeroHorasMensuales)
				&& Objects.equals(precioHora, other.precioHora);
	}

	@Override
	public String toString() {
		return String.format(
				"TrabajadorPorHoras [id=%s, nombre=%s, fechaNacimiento=%s, dni=%s, precioHora=%s, numeroHorasMensuales=%s]",
				id, nombre, fechaNacimiento, dni, precioHora, numeroHorasMensuales);
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return precioHora.multiply(new BigDecimal(numeroHorasMensuales));
	}

}
