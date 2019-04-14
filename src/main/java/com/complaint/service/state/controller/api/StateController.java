package com.complaint.service.state.controller.api;

import com.complaint.service.state.controller.StateResource;
import com.complaint.service.state.model.State;
import com.complaint.service.state.service.StateService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("state")
public class StateController implements StateResource {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity find() {
        Set<State> states = this.stateService.findAll();
        return ResponseEntity.ok(states);
    }
}
