package br.com.reclamacao.service.domain.service;

import br.com.reclamacao.service.domain.model.Estado;

import java.util.Set;

public interface EstadoService {

    Set<Estado> busca();

    Estado buscaPorId(String id);
}
