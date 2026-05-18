package com.ipartek.formacion.ejemplos.ipartex.dtos;

import java.time.LocalDateTime;

public record MensajeListadoDto(Long id, String texto, LocalDateTime momento, String usuario, Boolean rellenado,
		Long numeroMeGusta) {

}
