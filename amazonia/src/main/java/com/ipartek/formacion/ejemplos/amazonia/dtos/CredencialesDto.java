package com.ipartek.formacion.ejemplos.amazonia.dtos;

import jakarta.validation.constraints.NotBlank;

public record CredencialesDto(@NotBlank String email, @NotBlank String password) {

}
