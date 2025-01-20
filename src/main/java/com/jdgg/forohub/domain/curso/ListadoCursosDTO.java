package com.jdgg.forohub.domain.curso;

public record ListadoCursosDTO(
        Long id,
        String nombre,
        Categoria categoria) {

    public ListadoCursosDTO(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
