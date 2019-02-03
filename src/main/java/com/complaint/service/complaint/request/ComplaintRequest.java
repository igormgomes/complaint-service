package com.complaint.service.complaint.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;


@ApiModel(value = "Data of the complaint")
public class ComplaintRequest {

    @NotEmpty(message = "Title must be completed")
    @ApiModelProperty(value = "Title of the complaint", required = true, dataType = "String")
    private String title;

    @NotEmpty(message = "Description must be completed")
    @ApiModelProperty(value = "Description of the complaint", required = true, dataType = "String")
    private String description;

    @NotEmpty(message = "State id must be completed")
    @ApiModelProperty(value = "State id of the complaint", required = true, dataType = "String")
    private String stateId;

    @NotEmpty(message = "Company name must be completed")
    @ApiModelProperty(value = "Company name of the complaint", required = true, dataType = "String")
    private String companyName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateId() {
        return stateId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
