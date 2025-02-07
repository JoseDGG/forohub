package com.jdgg.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.jdgg.forohub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Permite el acceso directo al endpoint login.
        if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Leer el encabezado "Authorization" de la solicitud. Suele contener el toen JWT con el formato Bearer<token>.
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            try{

                //extrae los datos necesarios del token
                var roles = JWT.decode(token).getClaim("roles").asList(String.class);
                System.out.println("Roles obtenidos del token: " + roles); // LOG para depuración
                var tokenData = tokenService.getTokenData(token);
                if (tokenData != null){
                    //busca el usuario por su correo en la base de datos
                    var usuario = usuarioRepository.findBycorreoElectronico(tokenData.subject());
                    if(usuario != null){
                        var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
                        //Este objeto representa una autenticacion exitosa en Spring Security.
                        //Toma el usuario autenticado, una contraseña (aqui null porque no es necesaria) y sus roles/authorities.
                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities); // Forzamos un inicio de sesion
                        //Este objeto es un contenedor global donde se almacena la informacion de autenticacion del usuario.
                        //Esto indica a Spring Security que el usuario esta autenticado y autorizado.
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                    }
                }
            } catch (RuntimeException e) {
                System.err.println("Error procesando el token: " + e.getMessage());
            }
        }
        //Continua la cadena de filtros.
        filterChain.doFilter(request, response);

    }
}
