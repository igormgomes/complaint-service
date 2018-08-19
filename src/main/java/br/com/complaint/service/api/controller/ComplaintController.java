package br.com.complaint.service.api.controller;

import br.com.complaint.service.api.ComplaintResource;
import br.com.complaint.service.domain.model.Complaint;
import br.com.complaint.service.domain.service.ComplaintService;
import br.com.complaint.service.request.ComplaintRequest;
import br.com.complaint.service.request.mapper.ComplaintRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("complaint")
public class ComplaintController implements ComplaintResource {

    private ComplaintRequestMapper complaintRequestMapper;
    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintRequestMapper complaintRequestMapper, ComplaintService complaintService) {
        this.complaintRequestMapper = complaintRequestMapper;
        this.complaintService = complaintService;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity save(@Valid @RequestBody ComplaintRequest complaintRequest) {
        Complaint complaint = this.complaintService.save(this.complaintRequestMapper.parse(complaintRequest));
        return ResponseEntity.created(URI.create("complaint/" + complaint.getId())).build();
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity find() {
        Set<Complaint> complaints = this.complaintService.find();
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
        return ResponseEntity.accepted().build();
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@Valid @RequestBody ComplaintRequest complaintRequest, @PathVariable("id") String id) {
        this.complaintService.update(this.complaintRequestMapper.parse(complaintRequest), id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "/company/{company-id}/state/{state-id}")
    public ResponseEntity findByCompanyIdAndStateId(@PathVariable("company-id") String idEmpresa,
                                                    @PathVariable("state-id")String idEstado) {
        Set<Complaint> reclamacoes = this.complaintService.findByCompanyIdAndStateId(idEmpresa, idEstado);
        return ResponseEntity.ok(reclamacoes);
    }
}
