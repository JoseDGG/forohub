package com.jdgg.forohub.controller;

public record RegisterUserDTO(
        String nombre,
        String correoElectronico,
        String contrasena
) {
}
