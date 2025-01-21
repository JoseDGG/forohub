package com.jdgg.forohub.infra.security;

import com.jdgg.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Se debe implementar UserDetailsService aquí y UserDetails en la entidad Usuario
@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //UserDetails representa la informacion esencial del usuario, como el nombre de usuario, la contrasena y sus roles(autoridades).
    //Spring Security espera que el objeto UserDetails que se devuelva tenga la informacion necesaria para autenticar al usuario y darle acceso a los recursos.
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return usuarioRepository.findBycorreoElectronico(correo);
    }
}
