package com.jdgg.forohub.controller;

import com.jdgg.forohub.curso.Curso;
import com.jdgg.forohub.curso.CursoMostrarRegistroDTO;
import com.jdgg.forohub.curso.RegistrarCursoDTO;
import com.jdgg.forohub.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CursoMostrarRegistroDTO> registrarCurso(@RequestBody @Valid RegistrarCursoDTO registroCurso, UriComponentsBuilder uriComponentsBuilder){
        CursoMostrarRegistroDTO respuesta = cursoService.registrar(registroCurso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

}
