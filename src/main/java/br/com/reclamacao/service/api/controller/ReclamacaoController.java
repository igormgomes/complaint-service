package br.com.reclamacao.service.api.controller;

import br.com.reclamacao.service.api.ReclamacaoResource;
import br.com.reclamacao.service.domain.model.Reclamacao;
import br.com.reclamacao.service.domain.service.ReclamacaoService;
import br.com.reclamacao.service.request.ReclamacaoRequest;
import br.com.reclamacao.service.request.mapper.ReclamacaoRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("reclamacao")
public class ReclamacaoController implements ReclamacaoResource {

    private ReclamacaoRequestMapper reclamacaoRequestMapper;
    private ReclamacaoService reclamacaoService;

    @Autowired
    public ReclamacaoController(ReclamacaoRequestMapper reclamacaoRequestMapper, ReclamacaoService reclamacaoService) {
        this.reclamacaoRequestMapper = reclamacaoRequestMapper;
        this.reclamacaoService = reclamacaoService;
    }

    @Override
    public ResponseEntity salva(@Valid @RequestBody ReclamacaoRequest reclamacaoRequest) {
        Reclamacao reclamacao = this.reclamacaoService.salva(this.reclamacaoRequestMapper.parse(reclamacaoRequest));
        return ResponseEntity.created(URI.create("reclamacao/" + reclamacao.getId())).build();
    }

    @Override
    public ResponseEntity busca() {
        Set<Reclamacao> reclamacoes = this.reclamacaoService.busca();
        return ResponseEntity.ok(reclamacoes);
    }

    @Override
    public ResponseEntity buscaPorId(@PathVariable("id")String id) {
        Reclamacao reclamacao = this.reclamacaoService.buscaPorId(id);
        return ResponseEntity.ok(reclamacao);
    }

    @Override
    public ResponseEntity deleta(@PathVariable("id") String id) {
        this.reclamacaoService.deleta(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity edita(@Valid @RequestBody ReclamacaoRequest reclamacaoRequest, @PathVariable("id") String id) {
        this.reclamacaoService.edita(this.reclamacaoRequestMapper.parse(reclamacaoRequest), id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity buscaPorEmpresaEstado(@PathVariable("id-empresa") String idEmpresa,
                                                @PathVariable("id-estado")String idEstado) {
        Set<Reclamacao> reclamacoes = this.reclamacaoService.buscaPorEmpresaEstado(idEmpresa, idEstado);
        return ResponseEntity.ok(reclamacoes);
    }
}
