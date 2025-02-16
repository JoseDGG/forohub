package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.usuario.dto.RegistroUsuarioDTO;
import com.jdgg.forohub.domain.usuario.dto.RespuestaUsuarioDTO;
import com.jdgg.forohub.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaUsuarioDTO> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuario){
        RespuestaUsuarioDTO respuesta = usuarioService.registrar(registroUsuario);
        return ResponseEntity.ok(respuesta);
    }
}
