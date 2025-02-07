package com.jdgg.forohub.controller;

import com.jdgg.forohub.domain.usuario.dto.AuthUserDTO;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> LoginUser(@RequestBody @Valid AuthUserDTO authUserDTO) {
        System.out.println("Controller iniciado");
        //Se crea un objeto que almacena las credenciales del usuario (login, clave)
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.correoElectronico(),
                authUserDTO.contrasena());
        //Valida las credenciales enviadas(auth). authManager consulta AutenticacionService el cual debe contener @service y sobreescribir algún método de UserDetails, en este caso loadByUserName.
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        //Genera un token. Principal es el nombre de usuario(o identificador unico del usuario).
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("final del controller");
        //Crea una respuesta HTTP(200) con el token.
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @PostMapping("/register")
    public ResponseEntity<DatosJWTToken> registrarUsuario(@RequestBody @Valid RegisterUserDTO authUserDTO) {
        System.out.println("Controller iniciado");
        //Se crea un objeto que almacena las credenciales del usuario (login, clave)
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.correoElectronico(),
                authUserDTO.contrasena());
        //Valida las credenciales enviadas(auth). authManager consulta AutenticacionService el cual debe contener @service y sobreescribir algún método de UserDetails, en este caso loadByUserName.
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        //Genera un token. Principal es el nombre de usuario(o identificador unico del usuario).
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("final del controller");
        //Crea una respuesta HTTP(200) con el token.
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<DatosJWTToken> refreshUsuario(@RequestBody @Valid AuthUserDTO authUserDTO) {
        System.out.println("Controller iniciado");
        //Se crea un objeto que almacena las credenciales del usuario (login, clave)
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.correoElectronico(),
                authUserDTO.contrasena());
        //Valida las credenciales enviadas(auth). authManager consulta AutenticacionService el cual debe contener @service y sobreescribir algún método de UserDetails, en este caso loadByUserName.
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        //Genera un token. Principal es el nombre de usuario(o identificador unico del usuario).
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("final del controller");
        //Crea una respuesta HTTP(200) con el token.
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
