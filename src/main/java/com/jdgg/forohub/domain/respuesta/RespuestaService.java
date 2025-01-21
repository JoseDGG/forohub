package com.jdgg.forohub.domain.respuesta;

import com.jdgg.forohub.domain.ValidacionException;
import com.jdgg.forohub.domain.topico.*;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public RespuestaRespuestaDTO registrar(RegistroRespuestaDTO registroRespuestaDTO) {
        Usuario usuario = usuarioRepository.findById(registroRespuestaDTO.autorId())
                .orElseThrow(() -> new ValidacionException("Usuario no existe"));
        Topico topico = topicoRepository.findById(registroRespuestaDTO.topicoId())
                .orElseThrow(() -> new ValidacionException("Topico no existe"));

        Respuesta respuesta = respuestaRepository.save(new Respuesta(registroRespuestaDTO, topico, usuario));
        topico.actualizarRespuesta(respuesta);
        return new RespuestaRespuestaDTO(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getFechaCreacion(), respuesta.getAutor().getNombre());
    }

    @Transactional
    public RespuestaRespuestaDTO mostrarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));
        return new RespuestaRespuestaDTO(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getFechaCreacion(), respuesta.getAutor().getNombre());
    }

    @Transactional
    public void eliminarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));
        respuestaRepository.delete(respuesta);
    }
}


