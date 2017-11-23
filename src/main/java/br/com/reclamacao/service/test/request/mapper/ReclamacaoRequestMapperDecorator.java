package br.com.reclamacao.service.test.request.mapper;

import br.com.reclamacao.service.test.domain.model.Estado;
import br.com.reclamacao.service.test.domain.model.Reclamacao;
import br.com.reclamacao.service.test.domain.service.EstadoService;
import br.com.reclamacao.service.test.request.ReclamacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReclamacaoRequestMapperDecorator implements ReclamacaoRequestMapper {

    @Autowired
    @Qualifier("delegate")
    private ReclamacaoRequestMapper reclamacaoRequestMapper;

    @Autowired
    private EstadoService estadoService;

    @Override
    public Reclamacao parse(ReclamacaoRequest reclamacaoRequest) {
        Reclamacao reclamacao = this.reclamacaoRequestMapper.parse(reclamacaoRequest);

        Estado estado = this.estadoService.buscaPorId(reclamacao.getEstado().getId());
        reclamacao.setEstado(estado);

        return reclamacao;
    }
}