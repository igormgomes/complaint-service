package br.com.complaint.service.domain.service.impl;

import br.com.complaint.service.domain.model.Complaint;
import br.com.complaint.service.domain.repository.CompanyRepository;
import br.com.complaint.service.domain.repository.ComplaintRepository;
import br.com.complaint.service.domain.service.ComplaintService;
import br.com.complaint.service.exception.ComplaintNotFoundException;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private ComplaintRepository complaintRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository, CompanyRepository companyRepository) {
        this.complaintRepository = complaintRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Complaint save(Complaint complaint) {
        this.companyRepository.save(complaint.getCompany());
        return this.complaintRepository.save(complaint);
    }

    @Override
    public Set<Complaint> find() {
        Set<Complaint> complaints = Sets.newHashSet(this.complaintRepository.findAll());
        complaints.stream()
                .findFirst()
                .orElseThrow(ComplaintNotFoundException::new);
        return complaints;
    }

    @Override
    public void delete(String id) {
        findbyId(id);
        this.complaintRepository.delete(id);
    }

    @Override
    public void update(Complaint complaint, String id) {
        findbyId(id);
        complaint.setId(id);
        this.complaintRepository.save(complaint);
    }

    @Override
    public Complaint findbyId(String id) {
        return Optional.ofNullable(this.complaintRepository.findOne(id))
                .orElseThrow(() -> new ComplaintNotFoundException(
                        String.format("%s%s%s", "Compaint of the id ", id, " not found")));
    }

    @Override
    public Set<Complaint> findByCompanyIdAndStateId(String companyId, String stateId) {
        Set<Complaint> complaints = this.complaintRepository.findByCompany_IdAndAndState_Id(companyId, stateId);
        complaints.stream()
                .findFirst()
                .orElseThrow(ComplaintNotFoundException::new);
        return complaints;
    }
}