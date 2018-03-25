package br.com.complaint.service.request.mapper;

import br.com.complaint.service.domain.model.Complaint;
import br.com.complaint.service.domain.model.State;
import br.com.complaint.service.domain.service.StateService;
import br.com.complaint.service.request.ComplaintRequest;
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