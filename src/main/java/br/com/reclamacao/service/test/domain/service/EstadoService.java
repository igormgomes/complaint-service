package br.com.reclamacao.service.test.domain.service;

import br.com.reclamacao.service.test.domain.model.Estado;

import java.util.Set;

public interface EstadoService {

    Set<Estado> busca();

    Estado buscaPorId(String id);
}
