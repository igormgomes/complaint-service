package com.complaint.service.complaint.request;

import com.complaint.service.complaint.model.Complaint;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@DecoratedWith(ComplaintRequestMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface ComplaintRequestMapper {

    @Mappings(value = {
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "stateId", target = "state.id"),
            @Mapping(source = "companyName", target = "companyName"),
    })
    Complaint parse(ComplaintRequest complaintRequest);
}
