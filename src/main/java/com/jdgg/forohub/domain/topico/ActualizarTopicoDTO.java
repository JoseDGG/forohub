package com.jdgg.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull(message = "El ID del curso es obligatorio")
        Long id,
        String titulo,
        String mensaje,
        Boolean status
) {
}
