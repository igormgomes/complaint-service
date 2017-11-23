package br.com.reclamacao.service.domain.repository;

import br.com.reclamacao.service.domain.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {

    Set<Reclamacao> findByEmpresa_IdAndAndEstado_Id(String idEmpresa, String idEstado);
}
