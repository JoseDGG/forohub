package com.jdgg.forohub.curso;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(RegistrarCursoDTO registroCurso) {
        this.nombre = registroCurso.nombre();
        this.categoria = registroCurso.categoria();
    }
}
