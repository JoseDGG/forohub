package com.jdgg.forohub.controller;

import com.jdgg.forohub.service.TopicoService;
import com.jdgg.forohub.domain.topico.RegistroTopicoDTO;
import com.jdgg.forohub.domain.topico.RespuestaTopicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/topicos")
@RestController
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @GetMapping
    public ResponseEntity mostrarMensaje(){
        topicoService.listarTopico();
        return ResponseEntity.ok().build(); //!!!Falta actualizar el return!!!
    }

    @PostMapping
    public ResponseEntity<RespuestaTopicoDTO> registrarTopico(@RequestBody @Valid RegistroTopicoDTO datos){
        System.out.println("entré");
        topicoService.registrar(datos);
        return ResponseEntity.ok().build(); //!!!Falta actualizar el return!!!
    }
}
