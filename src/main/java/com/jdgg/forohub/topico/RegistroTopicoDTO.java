package com.jdgg.forohub.topico;

import jakarta.validation.constraints.NotBlank;

public record RegistroTopicoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        @NotBlank(message = "El ID del usuario (autor) es obligatorio")
        Long usuarioId,
        @NotBlank(message = "El ID del curso es obligatorio")
        Long crusoID
        ){}
