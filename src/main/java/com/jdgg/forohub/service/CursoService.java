package com.jdgg.forohub.service;

import com.jdgg.forohub.domain.curso.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public mostrarCursoDTO registrar(RegistrarCursoDTO registroCurso) {
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        return new mostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }

    public Page<ListadoCursosDTO> listarCursos(Pageable page) {
        return cursoRepository.findAll(page).map(ListadoCursosDTO::new);
    }

    public mostrarCursoDTO mostrarCurso(Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        return new mostrarCursoDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
