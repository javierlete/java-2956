package com.ipartek.formacion.ejemplos.amazonia.dtos;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank String nombre, @NotBlank String email, @NotBlank String rol) {

}
