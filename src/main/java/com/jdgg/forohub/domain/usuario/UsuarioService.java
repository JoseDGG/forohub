package com.jdgg.forohub.domain.usuario;

import com.jdgg.forohub.domain.usuario.dto.RegistroUsuarioDTO;
import com.jdgg.forohub.domain.usuario.dto.RespuestaUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public RespuestaUsuarioDTO registrar(@Valid RegistroUsuarioDTO registroUsuario) {
        Usuario respuesta = usuarioRepository.save(new Usuario(registroUsuario));
        return new RespuestaUsuarioDTO(respuesta.getNombre(), respuesta.getCorreoElectronico());
    }
}
