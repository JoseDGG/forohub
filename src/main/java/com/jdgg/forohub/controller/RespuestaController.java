package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.respuesta.dto.RegistroRespuestaDTO;
import com.jdgg.forohub.domain.respuesta.dto.RespuestaRespuestaDTO;
import com.jdgg.forohub.domain.respuesta.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequestMapping("/respuestas")
@RestController
public class RespuestaController {
    @Autowired
    RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<RespuestaRespuestaDTO> registrarRespuesta(@RequestBody @Valid RegistroRespuestaDTO registroRespuestaDTO, UriComponentsBuilder uriComponentsBuilder) {
        RespuestaRespuestaDTO respuesta = respuestaService.registrar(registroRespuestaDTO);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaRespuestaDTO> mostrarRespuesta(@PathVariable Long id) {
        RespuestaRespuestaDTO respuesta = respuestaService.mostrarRespuesta(id);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}

