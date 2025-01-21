package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.domain.respuesta.Respuesta;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Override
    Page<Topico> findAll(Pageable page);
}
