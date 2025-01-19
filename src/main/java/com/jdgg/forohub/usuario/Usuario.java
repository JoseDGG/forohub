package com.jdgg.forohub.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
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
}

