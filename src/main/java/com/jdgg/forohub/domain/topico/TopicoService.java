package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.controller.ListadoTopicosDTO;
import com.jdgg.forohub.domain.ValidacionException;
import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.curso.CursoRepository;
import com.jdgg.forohub.domain.respuesta.Respuesta;
import com.jdgg.forohub.domain.respuesta.RespuestaRepository;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    @Transactional
    public RespuestaRegistroTopicoDTO registrar(@Valid RegistroTopicoDTO registroTopicoDTO) {
        Usuario usuario = usuarioRepository.findById(registroTopicoDTO.usuarioId())
                .orElseThrow(() -> new ValidacionException("Usuario no existe"));
        Curso curso = cursoRepository.findById(registroTopicoDTO.cursoId())
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        Topico topico = topicoRepository.save(new Topico(registroTopicoDTO, usuario, curso));
        return new RespuestaRegistroTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre() , topico.getCurso().getNombre());
    }

    @Transactional
    public Page<ListadoTopicosDTO> listarTopico(Pageable page) {
        return topicoRepository.findAll(page).map(ListadoTopicosDTO::new);
    }

    @Transactional
    public RespuestaTopicoDTO mostrarTopico(Long id) {
        System.out.println("entro");
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        return new RespuestaTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre(),
                topico.getRespuestas().stream().map(Respuesta::getMensaje).toList());
    }

    @Transactional
    public RespuestaTopicoDTO actualizarTopico(ActualizarTopicoDTO actualizarTopico) {
        Topico topico = topicoRepository.findById(actualizarTopico.id())
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        topico.actualizarTopico(actualizarTopico);
        return new RespuestaTopicoDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre(),
                topico.getRespuestas().stream().map(Respuesta::getMensaje).toList());
    }

    @Transactional
    public void eliminarTopico(Long id) {
        topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        topicoRepository.deleteById(id);
    }

    @Transactional
    public List<ListadoRespuestasDeTopicoDTO> mostrarRespuestasDeTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));
        return respuestaRepository.findByTopicoId(topico.getId()).stream().map(ListadoRespuestasDeTopicoDTO::new).toList();
    }
}
