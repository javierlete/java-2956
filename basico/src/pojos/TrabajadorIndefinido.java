package pojos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class TrabajadorIndefinido extends Trabajador {
	private BigDecimal sueldoAnual;
	private Integer numeroPagas;

	public TrabajadorIndefinido(Long id, String nombre, LocalDate fechaNacimiento, String dni, BigDecimal sueldoAnual,
			Integer numeroPagas) {
		super(id, nombre, fechaNacimiento, dni);
		this.sueldoAnual = sueldoAnual;
		this.numeroPagas = numeroPagas;
	}

	public BigDecimal getSueldoAnual() {
		return sueldoAnual;
	}

	public void setSueldoAnual(BigDecimal sueldoAnual) {
		this.sueldoAnual = sueldoAnual;
	}

	public Integer getNumeroPagas() {
		return numeroPagas;
	}

	public void setNumeroPagas(Integer numeroPagas) {
		this.numeroPagas = numeroPagas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numeroPagas, sueldoAnual);
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
		TrabajadorIndefinido other = (TrabajadorIndefinido) obj;
		return Objects.equals(numeroPagas, other.numeroPagas) && Objects.equals(sueldoAnual, other.sueldoAnual);
	}

	@Override
	public String toString() {
		return String.format(
				"TrabajadorIndefinido [id=%s, nombre=%s, fechaNacimiento=%s, dni=%s, sueldoAnual=%s, numeroPagas=%s ]",
				id, nombre, fechaNacimiento, dni, sueldoAnual, numeroPagas);
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoAnual.divide(new BigDecimal(numeroPagas), 2, RoundingMode.HALF_UP);
	}

}
