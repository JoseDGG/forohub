package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.topico.Topico;
import com.jdgg.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record ListadoTopicosDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        String nombreCurso) {

    public ListadoTopicosDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
