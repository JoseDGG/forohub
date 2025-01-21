package com.jdgg.forohub.domain.respuesta;

import com.jdgg.forohub.domain.respuesta.dto.RegistroRespuestaDTO;
import com.jdgg.forohub.domain.topico.Topico;
import com.jdgg.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion; //sql automaticamente lo maneja

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private boolean solucion; //por defecto es 0

    public Respuesta(RegistroRespuestaDTO registroRespuestaDTO, Topico topico, Usuario usuario) {
        this.mensaje = registroRespuestaDTO.mensaje();
        this.topico = topico;
        this.autor = usuario;
    }

    public Respuesta(Respuesta respuesta) {
        this.id = respuesta.getId();
        this.mensaje = respuesta.getMensaje();
        this.topico = respuesta.getTopico();
        this.fechaCreacion = respuesta.getFechaCreacion();
        this.autor = respuesta.getAutor();
        this.solucion = respuesta.solucion;
    }

    @Override
    public String toString(){
        return String.format(
                "mensaje: %s" +
                "tópico: %s" +
                "fecha de creación: %s" +
                "autor: %s",
                mensaje, topico, fechaCreacion, autor);
    }
}
