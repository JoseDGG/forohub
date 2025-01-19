package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.usuario.Usuario;

public record RespuestaTopicoDTO(
        String titulo,
        String mensaje,
        Usuario usuario,
        Curso curso
) {
}
