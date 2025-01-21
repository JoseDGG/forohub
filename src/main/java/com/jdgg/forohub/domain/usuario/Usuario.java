package com.jdgg.forohub.domain.usuario;

import com.jdgg.forohub.domain.usuario.dto.RegistroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String contrasena;

    //Indica el rol que debe tener para poder acceder a diferentes metodos HTTP.
    @Enumerated(EnumType.STRING)
    private Rol rol; //Perfil

    //Constructor personalizado desde un DTO
    public Usuario(RegistroUsuarioDTO registroUsuario) {
        this.nombre = registroUsuario.nombre();
        this.correoElectronico = registroUsuario.correoElectronico().toLowerCase();
        this.contrasena = registroUsuario.contrasena();
        this.rol = Rol.USUARIO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve la autoridad con el prefijo ROLE_ requerido por Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol.name()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

