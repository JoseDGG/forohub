package com.jdgg.forohub.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarCursoDTO(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria) {
}
