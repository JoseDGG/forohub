package com.jdgg.forohub.domain.curso;

import com.jdgg.forohub.domain.ValidacionException;
import com.jdgg.forohub.domain.curso.dto.ActualizarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.ListadoCursosDTO;
import com.jdgg.forohub.domain.curso.dto.RegistrarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.mostrarCursoDTO;
import com.jdgg.forohub.domain.respuesta.Respuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public mostrarCursoDTO registrar(RegistrarCursoDTO registroCurso) {
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        return new mostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public Page<ListadoCursosDTO> listarCursos(Pageable page) {
        return cursoRepository.findAll(page).map(ListadoCursosDTO::new);
    }

    @Transactional
    public mostrarCursoDTO mostrarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        return new mostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public mostrarCursoDTO actualizarCurso(@Valid ActualizarCursoDTO actualizarCursoDTO) {
        Curso curso = cursoRepository.findById(actualizarCursoDTO.id())
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        curso.actualizar(actualizarCursoDTO);
        return new mostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
        cursoRepository.delete(curso);
    }
}
