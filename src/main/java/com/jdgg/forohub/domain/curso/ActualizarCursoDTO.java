package com.jdgg.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ActualizarCursoDTO(
        @NotNull(message = "El ID del curso es obligatorio")
        @Positive
        Long id,
        String nombre,
        Categoria categoria
) {
}
