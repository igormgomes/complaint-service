package com.complaint.service.complaint.controller.api;

import com.complaint.service.complaint.controller.ComplaintResource;
import com.complaint.service.complaint.request.ComplaintBuilder;
import com.complaint.service.complaint.request.ComplaintRequest;
import com.complaint.service.complaint.service.ComplaintService;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Complaint controller test")
public class ComplaintControllerTest {

    private ComplaintService complaintService;

    private ComplaintResource complaintResource;

    @BeforeEach
    public void beforeEach() {
        this.complaintService = mock(ComplaintService.class);
        this.complaintResource = new ComplaintController(this.complaintService);
    }

    @Test
    @DisplayName("Should test save complaint")
    public void shouldTestSaveComplaint() {
        when(this.complaintService.save(any()))
                .thenReturn(ComplaintBuilder.builder().build(getComplaintRequest()));

        ResponseEntity responseEntity = this.complaintResource.save(getComplaintRequest());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should test find complaint")
    public void shouldTestFindComplaint() {
        when(this.complaintService.find(anyString(), anyString()))
                .thenReturn(Sets.newHashSet(ComplaintBuilder.builder().build(getComplaintRequest())));

        ResponseEntity responseEntity = this.complaintResource.find("", "");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Should test find by id complaint")
    public void shouldTestFindByIdComplaint() {
        when(this.complaintService.findbyId(anyString()))
                .thenReturn(ComplaintBuilder.builder().build(getComplaintRequest()));

        ResponseEntity responseEntity = this.complaintResource.findById("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Should test find by id complaint")
    public void shouldTestDeleteComplaint() {
        doNothing()
                .when(this.complaintService).delete(anyString());

        ResponseEntity responseEntity = this.complaintResource.delete("1");

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should test update complaint")
    public void shouldTestUpdateComplaint() {
        doNothing()
                .when(this.complaintService).update(any(), anyString());

        ResponseEntity responseEntity = this.complaintResource.update(getComplaintRequest(), "1");

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    private ComplaintRequest getComplaintRequest() {
        ComplaintRequest complaintRequest = new ComplaintRequest();
        complaintRequest.setCompanyName("magazine");
        complaintRequest.setDescription("recharge");
        complaintRequest.setStateId("1");
        complaintRequest.setTitle("problem");
        return complaintRequest;
    }


}