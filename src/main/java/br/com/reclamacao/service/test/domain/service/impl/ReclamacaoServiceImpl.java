package br.com.reclamacao.service.test.domain.service.impl;

import br.com.reclamacao.service.test.domain.model.Reclamacao;
import br.com.reclamacao.service.test.domain.repository.ReclamacaoRepository;
import br.com.reclamacao.service.test.domain.service.ReclamacaoService;
import br.com.reclamacao.service.test.exception.ReclamacaoNotFoundException;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ReclamacaoServiceImpl implements ReclamacaoService {

    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    public ReclamacaoServiceImpl(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }

    @Override
    public Reclamacao salva(Reclamacao reclamacao) {
        return this.reclamacaoRepository.save(reclamacao);
    }

    @Override
    public Set<Reclamacao> busca() {
        Set<Reclamacao> reclamacoes = Sets.newHashSet(this.reclamacaoRepository.findAll());
        reclamacoes.stream()
                .findFirst()
                .orElseThrow(ReclamacaoNotFoundException::new);
        return reclamacoes;
    }

    @Override
    public void deleta(String id) {
        buscaPorId(id);
        this.reclamacaoRepository.delete(id);
    }

    @Override
    public void edita(Reclamacao reclamacao, String id) {
        buscaPorId(id);
        reclamacao.setId(id);
        this.reclamacaoRepository.save(reclamacao);
    }

    @Override
    public Reclamacao buscaPorId(String id) {
        return Optional.ofNullable(this.reclamacaoRepository.findOne(id))
                .orElseThrow(() -> new ReclamacaoNotFoundException(
                        String.format("%s%s%s", "Reclamação de id ", id, " não encontrada")));
    }
}