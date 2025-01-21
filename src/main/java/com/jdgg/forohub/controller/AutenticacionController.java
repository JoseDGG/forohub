package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.usuario.dto.AutenticacionUsuarioDTO;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO) {
        System.out.println("Controller iniciado");
        //Se crea un objeto que almacena las credenciales del usuario (login, clave)
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuarioDTO.correoElectronico(),
                autenticacionUsuarioDTO.contrasena());
        //Valida las credenciales enviadas(auth). authManager consulta AutenticacionService el cual debe contener @service y sobreescribir algún método de UserDetails, en este caso loadByUserName.
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        //Genera un token. Principal es el nombre de usuario(o identificador unico del usuario).
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("final del controller");
        //Crea una respuesta HTTP(200) con el token.
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
