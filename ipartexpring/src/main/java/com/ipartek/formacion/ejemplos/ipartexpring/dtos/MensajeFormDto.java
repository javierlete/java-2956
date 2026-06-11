package com.ipartek.formacion.ejemplos.ipartexpring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MensajeFormDto(@NotBlank @Size(max = 512) String texto) {

}
