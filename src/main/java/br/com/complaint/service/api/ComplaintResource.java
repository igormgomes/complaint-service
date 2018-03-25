package br.com.complaint.service.api;

import br.com.complaint.service.request.ComplaintRequest;
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
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Save complaint")
    ResponseEntity save(ComplaintRequest complaintRequest);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Find all complaint")
    ResponseEntity find();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Find by id complaint")
    ResponseEntity findById(String id);


    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accept"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Delete complaint")
    ResponseEntity delete(String id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Update complaint")
    ResponseEntity update(ComplaintRequest complaintRequest, String id);

    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accept"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Find complaint of the company and state")
    ResponseEntity findByCompanyIdAndStateId(String idEmpresa, String idEstado);
}
