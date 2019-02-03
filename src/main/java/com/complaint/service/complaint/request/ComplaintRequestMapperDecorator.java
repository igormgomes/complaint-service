package com.complaint.service.complaint.request;

import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.state.model.State;
import com.complaint.service.state.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ComplaintRequestMapperDecorator implements ComplaintRequestMapper {

    @Autowired
    @Qualifier("delegate")
    private ComplaintRequestMapper complaintRequestMapper;

    @Autowired
    private StateService stateService;

    @Override
    public Complaint parse(ComplaintRequest complaintRequest) {
        Complaint complaint = this.complaintRequestMapper.parse(complaintRequest);

        State state = this.stateService.findById(complaint.getState().getId());
        complaint.setState(state);

        return complaint;
    }
}