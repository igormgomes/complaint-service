package com.complaint.service.complaint.controller;

import com.complaint.service.complaint.request.ComplaintRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(tags = "Complaint")
public interface ComplaintResource {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 412, message = "Pre condition"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Save complaint")
    ResponseEntity save(ComplaintRequest complaintRequest);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Find all complaint or find by state id and company name")
    ResponseEntity find(String stateId, String companyName);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Find by id complaint")
    ResponseEntity findById(String id);


    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Accept"),
            @ApiResponse(code = 412, message = "Pre condition"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Delete complaint")
    ResponseEntity delete(String id);

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ok"),
            @ApiResponse(code = 412, message = "Pre condition"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Update complaint")
    ResponseEntity update(ComplaintRequest complaintRequest, String id);

}
