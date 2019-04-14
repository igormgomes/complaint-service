package com.complaint.service.complaint.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@ApiModel(value = "Data of the complaint")
public class ComplaintRequest {

    @NotBlank(message = "Title must be completed")
    @ApiModelProperty(value = "Title of the complaint", required = true, dataType = "String")
    private String title;

    @NotBlank(message = "Description must be completed")
    @ApiModelProperty(value = "Description of the complaint", required = true, dataType = "String")
    private String description;

    @NotBlank(message = "State id must be completed")
    @ApiModelProperty(value = "State id of the complaint", required = true, dataType = "String")
    private String stateId;

    @NotBlank(message = "Company name must be completed")
    @ApiModelProperty(value = "Company name of the complaint", required = true, dataType = "String")
    private String companyName;
}
