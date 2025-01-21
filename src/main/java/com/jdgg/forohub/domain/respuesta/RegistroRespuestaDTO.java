package com.jdgg.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistroRespuestaDTO(
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        @NotNull(message = "El ID del tópico es obligatorio")
        @Positive
        Long topicoId,
        @NotNull(message = "El ID del autor es obligatorio")
        @Positive
        Long autorId
) {
}
