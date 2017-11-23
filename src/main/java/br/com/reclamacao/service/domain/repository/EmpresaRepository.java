package br.com.reclamacao.service.domain.repository;

import br.com.reclamacao.service.domain.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {
}
