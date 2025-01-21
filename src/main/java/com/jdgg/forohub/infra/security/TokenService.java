package com.jdgg.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jdgg.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //define el algoritmo de firma, y una clave para firmar y validar el token.
            var token = JWT.create()
                    .withIssuer("forohub") //Establece el emisor del token.
                    .withSubject(usuario.getCorreoElectronico()) //Establece el "subject" (puede ser el nombre de usuario o el correo)
                    .withClaim("id", usuario.getId()) //Agrega informacion personalizada (o claims) al payload del token JWT.
                    .withClaim("roles", usuario.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .withExpiresAt(generarFechaExpiracion()) //Establece el tiempo de expiracion.
                    .sign(algorithm); //Firma el token con HMAC y la clave secreta.
            System.out.println("Token generado: " + token); // LOG para depuración
            return  token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public TokenDTO getTokenData(String token) {
        if (token == null) {
            throw new RuntimeException("El token es nulo");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    //Verifica el token recibido con la misma clave secreta y el emisor del token coincidan. Tambien que la fecha de expiracion sea valida.
                    .verify(token);

            //Devuelve el valor del campo. El subject suele ser un identificador unico del usuario, como su nombre de usuario o correo electronico.
            //Este campo debe haber sido incluido cuando el token fue generado.
            String subject = verifier.getSubject();
            List<String> roles = verifier.getClaim("roles").asList(String.class);

            System.out.println("subject: " + subject); //Log de depuracion
            System.out.println("roles: " + roles); //Log de depuracion

            if (roles == null) {
                throw new RuntimeException("Role invalido");
            }
            if (subject == null) {
                throw new RuntimeException("Subject invalido");
            }
            String role = roles.get(0);
            return new TokenDTO(subject, role);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error verificando el token: " + exception.toString(), exception);
        }
    }

    //LocalDateTime.now(): Obtiene la fecha y hora actuales del sistema, pero sin informacion de zona horaria.
//.plusHours(2): Agrega 2 horas a la fecha y hora actual obtenida en el paso anterior.
//.toInstant(ZoneOffset.of("-05:00")): Convierte LocalDateTime, que representa un momento en el tiempo sin cosiderar la zona horaria. Es un punto universal en el tiempo.
//Zoneoffset.of("-05:00"): especifica el desplazamiento horario de 5 horas detras de UTC.
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}


