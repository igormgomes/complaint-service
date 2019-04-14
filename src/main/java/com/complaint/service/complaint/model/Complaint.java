package com.complaint.service.complaint.model;

import com.complaint.service.state.model.State;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "Complaint")
public class Complaint {

    @Id
    private String id;
    private String title;
    private String description;
    private State state;
    private String companyName;

    public void setState(State state) {
        this.state = state;
    }

    public void setId(String id) {
        this.id = id;
    }
}
