package com.jdgg.forohub.domain.topico.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ActualizarTopicoDTO(
        @NotNull(message = "El ID del curso es obligatorio")
        @Positive
        Long id,
        String titulo,
        String mensaje,
        Boolean status
) {
}
