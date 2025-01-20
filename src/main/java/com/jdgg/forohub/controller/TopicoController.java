package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.topico.ActualizarTopicoDTO;
import com.jdgg.forohub.domain.topico.Topico;
import com.jdgg.forohub.service.TopicoService;
import com.jdgg.forohub.domain.topico.RegistroTopicoDTO;
import com.jdgg.forohub.domain.topico.RespuestaTopicoDTO;
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

@RequestMapping("/topicos")
@RestController
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> registrarTopico(@RequestBody @Valid RegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder){
        RespuestaTopicoDTO respuesta = topicoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicosDTO>> ListarTopicos(@PageableDefault(size = 5) Pageable page){
         Page<ListadoTopicosDTO> listado = topicoService.listarTopico(page);
        return ResponseEntity.ok(listado);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> mostrarTopico(@PathVariable Long id){
        RespuestaTopicoDTO topico = topicoService.mostrarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO){
        RespuestaTopicoDTO respuesta = topicoService.actualizarTopico(actualizarTopicoDTO);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
