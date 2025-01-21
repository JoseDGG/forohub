package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

//los datos pertenecen a Respuesta
public record ListadoRespuestasDeTopicoDTO(
        String topico,
        String mensaje,
        String autor,
        LocalDateTime fechaCreacion
) {
    public ListadoRespuestasDeTopicoDTO(Respuesta respuesta) {
        this(respuesta.getTopico().getTitulo(), respuesta.getMensaje(),
                respuesta.getAutor().getNombre(), respuesta.getFechaCreacion());
    }
}
