package com.jdgg.forohub.domain.respuesta.dto;

import java.time.LocalDateTime;

public record RespuestaRespuestaDTO(
        Long id,
        String mensaje,
        String topico,
        LocalDateTime fecha,
        String Autor
) {
}
