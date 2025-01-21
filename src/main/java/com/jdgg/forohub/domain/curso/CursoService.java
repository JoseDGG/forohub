package com.jdgg.forohub.domain.curso;

import com.jdgg.forohub.domain.ValidacionException;
import com.jdgg.forohub.domain.curso.dto.ActualizarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.ListadoCursosDTO;
import com.jdgg.forohub.domain.curso.dto.RegistrarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.MostrarCursoDTO;
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
    public MostrarCursoDTO registrar(RegistrarCursoDTO registroCurso) {
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        return new MostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public Page<ListadoCursosDTO> listarCursos(Pageable page) {
        return cursoRepository.findAll(page).map(ListadoCursosDTO::new);
    }

    @Transactional
    public MostrarCursoDTO mostrarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        return new MostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public MostrarCursoDTO actualizarCurso(@Valid ActualizarCursoDTO actualizarCursoDTO) {
        Curso curso = cursoRepository.findById(actualizarCursoDTO.id())
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        curso.actualizar(actualizarCursoDTO);
        return new MostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    @Transactional
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
        cursoRepository.delete(curso);
    }
}
