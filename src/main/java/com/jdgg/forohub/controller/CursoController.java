package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.curso.*;
import com.jdgg.forohub.domain.curso.dto.ActualizarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.ListadoCursosDTO;
import com.jdgg.forohub.domain.curso.dto.RegistrarCursoDTO;
import com.jdgg.forohub.domain.curso.dto.MostrarCursoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MostrarCursoDTO> registrarCurso(@RequestBody @Valid RegistrarCursoDTO registroCurso, UriComponentsBuilder uriComponentsBuilder){
        MostrarCursoDTO respuesta = cursoService.registrar(registroCurso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoCursosDTO>> listarCursos(@PageableDefault(size = 10) Pageable page){
        var listado = cursoService.listarCursos(page);
        return ResponseEntity.ok(listado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarCursoDTO> mostrarCurso(@PathVariable("id") Long id){
        MostrarCursoDTO curso = cursoService.mostrarCurso(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping
    public ResponseEntity<MostrarCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO actualizarCursoDTO){
        MostrarCursoDTO cursoActualizado = cursoService.actualizarCurso(actualizarCursoDTO);
        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCurso(@PathVariable("id") Long id){
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
