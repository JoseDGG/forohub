package com.jdgg.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdgg.forohub.domain.usuario.Usuario;
import com.jdgg.forohub.domain.curso.Curso;
import com.jdgg.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion; //sql automaticamente lo maneja

    private boolean status; //El estado del topico sera TRUE o 1 hasta que se marque como resuelto.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(RegistroTopicoDTO datos, Usuario usuario, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.status = true;
        this.autor = usuario;
        this.curso = curso;
    }

    public void actualizarTopico(ActualizarTopicoDTO actualizarTopico) {
        if (actualizarTopico.titulo() != null) {
            this.titulo = actualizarTopico.titulo();
        }
        if (actualizarTopico.mensaje() != null) {
            this.mensaje = actualizarTopico.mensaje();
        }
        if (actualizarTopico.status() != null) {
            this.status = actualizarTopico.status();
        }
    }

    public void actualizarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }
}



