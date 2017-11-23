package br.com.reclamacao.service.domain.service.impl;

import br.com.reclamacao.service.domain.model.Reclamacao;
import br.com.reclamacao.service.domain.repository.EmpresaRepository;
import br.com.reclamacao.service.domain.repository.ReclamacaoRepository;
import br.com.reclamacao.service.domain.service.ReclamacaoService;
import br.com.reclamacao.service.exception.ReclamacaoNotFoundException;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReclamacaoServiceImpl implements ReclamacaoService {

    private ReclamacaoRepository reclamacaoRepository;
    private EmpresaRepository empresaRepository;

    @Autowired
    public ReclamacaoServiceImpl(ReclamacaoRepository reclamacaoRepository, EmpresaRepository empresaRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Reclamacao salva(Reclamacao reclamacao) {
        this.empresaRepository.save(reclamacao.getEmpresa());
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

    @Override
    public Set<Reclamacao> buscaPorEmpresaEstado(String idEmpresa, String idEstado) {
        Set<Reclamacao> reclamacoes = this.reclamacaoRepository.findByEmpresa_IdAndAndEstado_Id(idEmpresa, idEstado);
        reclamacoes.stream()
                .findFirst()
                .orElseThrow(ReclamacaoNotFoundException::new);
        return reclamacoes;
    }
}