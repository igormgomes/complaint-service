package com.complaint.service.complaint.service;


import com.complaint.service.complaint.model.Complaint;

import java.util.Set;

public interface ComplaintService {

    Complaint save(Complaint complaint);

    Set<Complaint> find(String stateId, String companyName);

    void delete(String id);

    void update(Complaint complaint, String id);

    Complaint findbyId(String id);
}
