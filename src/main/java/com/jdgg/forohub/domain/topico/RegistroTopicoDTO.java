package com.jdgg.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistroTopicoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        @NotNull(message = "El ID del usuario (autor) es obligatorio")
        @Positive
        Long usuarioId,
        @NotNull(message = "El ID del curso es obligatorio")
        @Positive
        Long cursoId
){}
