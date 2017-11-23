package br.com.reclamacao.service.test.request.mapper;

import br.com.reclamacao.service.test.domain.model.Reclamacao;
import br.com.reclamacao.service.test.request.ReclamacaoRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@DecoratedWith(ReclamacaoRequestMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface ReclamacaoRequestMapper {

    @Mappings(value = {
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "local", target = "estado.id"),
            @Mapping(source = "nomeEmpresa", target = "empresa.nome"),
    })
    Reclamacao parse(ReclamacaoRequest reclamacaoRequest);
}
