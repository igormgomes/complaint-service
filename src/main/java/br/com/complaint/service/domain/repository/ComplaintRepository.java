package br.com.complaint.service.domain.repository;

import br.com.complaint.service.domain.model.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ComplaintRepository extends MongoRepository<Complaint, String> {

    Set<Complaint> findByCompany_IdAndAndState_Id(String idEmpresa, String idEstado);
}
