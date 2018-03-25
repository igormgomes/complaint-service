package br.com.complaint.service.api.controller;

import br.com.complaint.service.api.StateResource;
import br.com.complaint.service.domain.model.State;
import br.com.complaint.service.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("state")
public class StateController implements StateResource {

    private StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity find() {
        Set<State> states = this.stateService.find();
        return ResponseEntity.ok(states);
    }
}
