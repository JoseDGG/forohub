package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.usuario.RegistroUsuarioDTO;
import com.jdgg.forohub.domain.usuario.RespuestaUsuarioDTO;
import com.jdgg.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<RespuestaUsuarioDTO> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuario){
        RespuestaUsuarioDTO respuesta = usuarioService.registrar(registroUsuario);
        return ResponseEntity.ok(respuesta);
    }
}
