package com.jdgg.forohub.domain.curso.dto;

import com.jdgg.forohub.domain.curso.Categoria;
import com.jdgg.forohub.domain.curso.Curso;

public record ListadoCursosDTO(
        Long id,
        String nombre,
        Categoria categoria) {

    public ListadoCursosDTO(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
