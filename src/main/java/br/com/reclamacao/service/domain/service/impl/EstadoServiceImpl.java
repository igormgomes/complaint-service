package br.com.reclamacao.service.domain.service.impl;

import br.com.reclamacao.service.domain.model.Estado;
import br.com.reclamacao.service.domain.repository.EstadoRepository;
import br.com.reclamacao.service.domain.service.EstadoService;
import br.com.reclamacao.service.exception.EstadoNotFoundException;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class EstadoServiceImpl implements EstadoService {

    private EstadoRepository estadoRepository;

    @Autowired
    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Set<Estado> busca() {
        return Sets.newHashSet(this.estadoRepository.findAll());
    }

    @Override
    public Estado buscaPorId(String id) {
        return Optional.ofNullable(this.estadoRepository.findOne(id))
                .orElseThrow(() -> new EstadoNotFoundException
                        (String.format("%s%s%s", "Estado de id", id, "não encontrado")));
    }
}