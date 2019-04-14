package com.complaint.service.complaint.repository;

import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.state.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ComplaintRepository extends MongoRepository<Complaint, String> {

    Set<Complaint> findAllByStateAndCompanyName(State state, String companyName);
}
