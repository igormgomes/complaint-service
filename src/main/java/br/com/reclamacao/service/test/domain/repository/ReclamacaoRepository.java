package br.com.reclamacao.service.test.domain.repository;

import br.com.reclamacao.service.test.domain.model.Reclamacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String> {
}
