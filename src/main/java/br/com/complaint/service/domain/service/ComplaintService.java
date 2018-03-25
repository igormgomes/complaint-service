package br.com.complaint.service.domain.service;


import br.com.complaint.service.domain.model.Complaint;

import java.util.Set;

public interface ComplaintService {

    Complaint save(Complaint complaint);

    Set<Complaint> find();

    void delete(String id);

    void update(Complaint complaint, String id);

    Complaint findbyId(String id);

    Set<Complaint> findByCompanyIdAndStateId(String companyId, String stateId);
}
