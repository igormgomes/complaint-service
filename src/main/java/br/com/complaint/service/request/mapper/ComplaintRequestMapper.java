package br.com.complaint.service.request.mapper;

import br.com.complaint.service.domain.model.Complaint;
import br.com.complaint.service.request.ComplaintRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@DecoratedWith(ComplaintRequestMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface ComplaintRequestMapper {

    @Mappings(value = {
            @Mapping(source = "title", target = "titulo"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "stateId", target = "state.id"),
            @Mapping(source = "companyName", target = "company.name"),
    })
    Complaint parse(ComplaintRequest complaintRequest);
}
