package com.jdgg.forohub.domain.topico;

import com.jdgg.forohub.domain.curso.Curso;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Override
    Page<Topico> findAll(Pageable page);
}
