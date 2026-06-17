package com.ipartek.formacion.ejemplos.amazonia.dtos;

import java.math.BigDecimal;

public record ProductoDto(Long id, String nombre, String descripcion, BigDecimal precio) {

}
