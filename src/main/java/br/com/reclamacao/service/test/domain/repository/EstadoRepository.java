package br.com.reclamacao.service.test.domain.repository;

import br.com.reclamacao.service.test.domain.model.Estado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends MongoRepository<Estado, String> {
}
