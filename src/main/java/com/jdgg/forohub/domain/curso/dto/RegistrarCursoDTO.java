package com.jdgg.forohub.domain.curso.dto;

import com.jdgg.forohub.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarCursoDTO(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria) {
}
