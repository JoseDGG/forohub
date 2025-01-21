package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/topicos")
@RestController
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping
    public ResponseEntity<RespuestaRegistroTopicoDTO> registrarTopico(@RequestBody @Valid RegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder){
        RespuestaRegistroTopicoDTO respuesta = topicoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicosDTO>> ListarTopicos(@PageableDefault(size = 5) Pageable page){
         Page<ListadoTopicosDTO> listado = topicoService.listarTopico(page);
        return ResponseEntity.ok(listado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoDTO> mostrarTopico(@PathVariable Long id){
        RespuestaTopicoDTO topico = topicoService.mostrarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<List<ListadoRespuestasDeTopicoDTO>> mostrarRespuestasDeTopico(@PathVariable Long id){
        List<ListadoRespuestasDeTopicoDTO> RespuestasDeTopico = topicoService.mostrarRespuestasDeTopico(id);
        return ResponseEntity.ok(RespuestasDeTopico);
    }

    @PutMapping
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO){
        RespuestaTopicoDTO respuesta = topicoService.actualizarTopico(actualizarTopicoDTO);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
