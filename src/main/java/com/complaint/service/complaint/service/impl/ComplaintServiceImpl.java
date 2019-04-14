package com.complaint.service.complaint.service.impl;

import com.complaint.service.complaint.exception.ComplaintNotFoundException;
import com.complaint.service.complaint.exception.ComplaintPreConditionException;
import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.complaint.repository.ComplaintRepository;
import com.complaint.service.complaint.service.ComplaintService;
import com.complaint.service.state.model.State;
import com.complaint.service.state.service.StateService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static org.springframework.util.StringUtils.isEmpty;

@Service
class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final StateService stateService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, StateService stateService) {
        this.complaintRepository = complaintRepository;
        this.stateService = stateService;
    }

    @Override
    public Complaint save(Complaint complaint) {
        State state = this.stateService.findById(complaint.getState().getId());
        complaint.setState(state);
        return this.complaintRepository.save(complaint);
    }

    @Override
    public Set<Complaint> find(String stateId, String companyName) {
        if (isEmpty(stateId) || isEmpty(companyName)) {
            Set<Complaint> complaints = Sets.newHashSet(this.complaintRepository.findAll());
            if (complaints.isEmpty()) {
                throw new ComplaintNotFoundException();
            }
            return complaints;
        }

        State state = this.stateService.findById(stateId);
        Set<Complaint> complaints = this.complaintRepository.findAllByStateAndCompanyName(state, companyName);
        if (complaints.isEmpty()) {
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
        State state = this.stateService.findById(complaint.getState().getId());
        complaint.setState(state);
        complaint.setId(id);
        this.complaintRepository.save(complaint);
    }

    private Optional<Complaint> find(String id){
        return this.complaintRepository.findById(id);
    }
}