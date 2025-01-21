package com.jdgg.forohub.domain.topico.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String nombreUsuario,
        String nombreCurso,
        List<String> respuestas
) {
}
