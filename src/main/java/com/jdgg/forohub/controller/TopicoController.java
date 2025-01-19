package com.jdgg.forohub.controller;

import com.jdgg.forohub.service.TopicoService;
import com.jdgg.forohub.topico.RegistroTopicoDTO;
import com.jdgg.forohub.topico.RespuestaTopicoDTO;
import com.jdgg.forohub.topico.Topico;
import com.jdgg.forohub.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
