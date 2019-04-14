package com.complaint.service.state.controller.api;

import com.complaint.service.state.controller.StateResource;
import com.complaint.service.state.model.State;
import com.complaint.service.state.service.StateService;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("State controller test")
public class StateControllerTest {

    private StateService stateService;

    private StateResource stateResource;

    @BeforeEach
    public void beforeEach() {
        this.stateService = mock(StateService.class);
        this.stateResource = new StateController(this.stateService);
    }

    @Test
    @DisplayName("Should test find state")
    public void shouldTestFindState() {
        when(this.stateService.findAll())
                .thenReturn(Sets.newHashSet(getState()));

        ResponseEntity responseEntity = this.stateResource.find();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    private State getState() {
        return State.builder()
                .id("1")
                .name("SP")
                .build();
    }
}