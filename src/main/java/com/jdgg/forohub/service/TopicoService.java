package com.jdgg.forohub.service;

import com.jdgg.forohub.controller.ListadoTopicosDTO;
import com.jdgg.forohub.domain.ValidacionException;
import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.curso.CursoRepository;
import com.jdgg.forohub.domain.topico.*;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;

    public RespuestaTopicoDTO registrar(RegistroTopicoDTO datos) {
        Usuario usuario = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new ValidacionException("Usuario no existe"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new ValidacionException("Curso no existe"));

        Topico topico = topicoRepository.save(new Topico(datos, usuario, curso));
        return new RespuestaTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre() , topico.getCurso().getNombre(), List.of());
    }

    public Page<ListadoTopicosDTO> listarTopico(Pageable page) {
        return topicoRepository.findAll(page).map(ListadoTopicosDTO::new);
    }

    public RespuestaTopicoDTO mostrarTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return new RespuestaTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre(), topico.getRespuestas());
    }

    public RespuestaTopicoDTO actualizarTopico(ActualizarTopicoDTO actualizarTopico) {
        Topico topico = topicoRepository.findById(actualizarTopico.id())
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        topico.actualizarTopico(actualizarTopico);
        return new RespuestaTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre(), topico.getRespuestas());
    }

    public void eliminarTopico(Long id) {
        topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        topicoRepository.deleteById(id);
    }
}
