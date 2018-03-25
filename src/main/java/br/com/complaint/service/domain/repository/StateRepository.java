package br.com.complaint.service.domain.repository;

import br.com.complaint.service.domain.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends MongoRepository<State, String> {
}
