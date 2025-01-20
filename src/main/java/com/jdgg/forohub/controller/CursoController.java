package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.curso.RegistrarCursoDTO;
import com.jdgg.forohub.service.CursoService;
import com.jdgg.forohub.domain.curso.ListadoCursosDTO;
import com.jdgg.forohub.domain.curso.mostrarCursoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//!!!Solo debe tener acceso a registrar cursos el administrador. Debe añadirse al crear la configuracion de seguridad!!!
@RequestMapping("/cursos")
@RestController
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<mostrarCursoDTO> registrarCurso(@RequestBody @Valid RegistrarCursoDTO registroCurso, UriComponentsBuilder uriComponentsBuilder){
        mostrarCursoDTO respuesta = cursoService.registrar(registroCurso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<ListadoCursosDTO>> listarCursos(@PageableDefault(size = 10) Pageable page){
        var listado = cursoService.listarCursos(page);
        return ResponseEntity.ok(listado);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<mostrarCursoDTO> mostrarCurso(@PathVariable("id") Long id){
        mostrarCursoDTO curso = cursoService.mostrarCurso(id);
        return ResponseEntity.ok(curso);
    }
}
