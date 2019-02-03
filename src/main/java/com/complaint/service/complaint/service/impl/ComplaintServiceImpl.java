package com.complaint.service.complaint.service.impl;

import com.complaint.service.complaint.exception.ComplaintNotFoundException;
import com.complaint.service.complaint.exception.ComplaintPreConditionException;
import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.complaint.repository.ComplaintRepository;
import com.complaint.service.complaint.service.ComplaintService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class ComplaintServiceImpl implements ComplaintService {

    private ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint save(Complaint complaint) {
        return this.complaintRepository.save(complaint);
    }

    @Override
    public Set<Complaint> find() {
        Set<Complaint> complaints = Sets.newHashSet(this.complaintRepository.findAll());
        if(complaints.isEmpty()){
            throw new ComplaintNotFoundException();
        }
        return complaints;
    }

    @Override
    public Complaint findbyId(String id) {
        return this.find(id)
                .orElseThrow(() -> new ComplaintNotFoundException(String.format("%s%s%s", "Complaint id ", id, " not found")));
    }

    @Override
    public void delete(String id) {
        Complaint complaint = this.find(id)
                .orElseThrow(() -> new ComplaintPreConditionException(String.format("%s%s%s", "Complaint id ", id, " not found")));
        this.complaintRepository.delete(complaint);
    }

    @Override
    public void update(Complaint complaint, String id) {
        this.find(id)
                .orElseThrow(() -> new ComplaintPreConditionException(String.format("%s%s%s", "Complaint id ", id, " not found")));
        complaint.setId(id);
        this.complaintRepository.save(complaint);
    }

    private Optional<Complaint> find(String id){
        return this.complaintRepository.findById(id);
    }
}