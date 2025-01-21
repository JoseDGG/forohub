package com.jdgg.forohub.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query("select m from Respuesta m where m.topico.id = :topicoId")
    List<Respuesta> findByTopicoId(@Param("topicoId") Long id);
}
