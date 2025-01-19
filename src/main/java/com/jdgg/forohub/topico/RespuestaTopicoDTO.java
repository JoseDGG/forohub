package com.jdgg.forohub.topico;

import com.jdgg.forohub.curso.Curso;
import com.jdgg.forohub.usuario.Usuario;

public record RespuestaTopicoDTO(
        String titulo,
        String mensaje,
        Usuario usuario,
        Curso curso
) {
}
