package com.jdgg.forohub.domain.topico.dto;

import java.time.LocalDateTime;

public record RespuestaRegistroTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String nombreUsuario,
        String nombreCurso
) {
}
