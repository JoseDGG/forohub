package com.jdgg.forohub.service;

import com.jdgg.forohub.curso.Curso;
import com.jdgg.forohub.curso.CursoMostrarRegistroDTO;
import com.jdgg.forohub.curso.CursoRepository;
import com.jdgg.forohub.curso.RegistrarCursoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public CursoMostrarRegistroDTO registrar(RegistrarCursoDTO registroCurso) {
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        return new CursoMostrarRegistroDTO(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
