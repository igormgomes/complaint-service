package br.com.reclamacao.service.domain.repository;

import br.com.reclamacao.service.domain.model.Estado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends MongoRepository<Estado, String> {
}
