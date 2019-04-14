package com.complaint.service.complaint.controller.api;

import com.complaint.service.complaint.controller.ComplaintResource;
import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.complaint.request.ComplaintBuilder;
import com.complaint.service.complaint.request.ComplaintRequest;
import com.complaint.service.complaint.service.ComplaintService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("complaint")
public class ComplaintController implements ComplaintResource {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity save(@Valid @RequestBody ComplaintRequest complaintRequest) {
        Complaint complaint = this.complaintService.save(ComplaintBuilder.builder().build(complaintRequest));
        return ResponseEntity.created(URI.create("complaint/" + complaint.getId())).build();
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity find(@RequestParam(name = "stateId", required = false) String stateId,
                               @RequestParam(name = "companyName", required = false) String companyName) {
        Set<Complaint> complaints = this.complaintService.find(stateId, companyName);
        return ResponseEntity.ok(complaints);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity findById(@PathVariable("id")String id) {
        Complaint complaint = this.complaintService.findbyId(id);
        return ResponseEntity.ok(complaint);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        this.complaintService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@Valid @RequestBody ComplaintRequest complaintRequest, @PathVariable("id") String id) {
        this.complaintService.update(ComplaintBuilder.builder().build(complaintRequest), id);
        return ResponseEntity.noContent().build();
    }
}
