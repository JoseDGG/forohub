package com.jdgg.forohub.domain.curso.dto;

import com.jdgg.forohub.domain.curso.Categoria;

public record MostrarCursoDTO(
        Long id,
        String nombre,
        Categoria categoria
) {
}
