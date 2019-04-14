package com.complaint.service.complaint.request;

import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.state.model.State;

public class ComplaintBuilder {

    public static ComplaintBuilder builder() {
        return new ComplaintBuilder();
    }

    public Complaint build(ComplaintRequest complaintRequest) {
        return Complaint.builder()
                .title(complaintRequest.getTitle())
                .description(complaintRequest.getDescription())
                .state(State.builder().id(complaintRequest.getStateId()).build())
                .companyName(complaintRequest.getCompanyName())
                .build();
    }
}