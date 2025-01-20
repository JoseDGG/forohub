package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record RespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String nombreUsuario,
        String nombreCurso,
        List<Respuesta> respuestas
) {
}
