package com.complaint.service.state.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "State")
public class State {

    @Id
    private String id;
    private String name;

}
