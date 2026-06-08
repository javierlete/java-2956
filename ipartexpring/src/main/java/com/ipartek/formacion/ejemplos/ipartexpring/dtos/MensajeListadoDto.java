package com.ipartek.formacion.ejemplos.ipartexpring.dtos;

import java.time.LocalDateTime;

public record MensajeListadoDto(Long id, String texto, LocalDateTime momento, String usuario, Boolean rellenado,
		Long numeroMeGusta, Long numeroRespuestas) {

}
