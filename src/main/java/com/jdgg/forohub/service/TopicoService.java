package com.jdgg.forohub.service;

import com.jdgg.forohub.domain.topico.RegistroTopicoDTO;
import com.jdgg.forohub.domain.topico.Topico;
import com.jdgg.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public void registrar( RegistroTopicoDTO datos) {
        Topico topico = topicoRepository.save(new Topico(datos));
    }

    public void listarTopico() {
    }
}
