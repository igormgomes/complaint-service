package com.complaint.service.state.service.impl;

import com.complaint.service.state.exception.StateNotFoundException;
import com.complaint.service.state.exception.StatePreConditionException;
import com.complaint.service.state.model.State;
import com.complaint.service.state.repository.StateRepository;
import com.complaint.service.state.service.StateService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StateServiceTest {

    private StateRepository stateRepository;
    private StateService stateService;

    @BeforeEach
    public void beforeEach() {
        this.stateRepository = mock(StateRepository.class);
        this.stateService = new StateServiceImpl(this.stateRepository);
    }

    @Test
    @DisplayName("Should throw StateNotFoundException in find all")
    public void shouldThrowStateNotFoundExceptionInFindAll() {
        when(this.stateRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(StateNotFoundException.class, () -> this.stateService.findAll());
    }

    @Test
    @DisplayName("Should throw StateNotFoundException in find all")
    public void shouldTestFindAll() {
        State state = new State();
        state.setId("1");
        state.setName("SP");
        when(this.stateRepository.findAll())
                .thenReturn(Lists.newArrayList(state));

        Set<State> states = this.stateService.findAll();

        assertEquals(1, states.size());
    }

    @Test
    @DisplayName("Should throw StatePreConditionException in find")
    public void shouldThrowStatePreConditionExceptionInFind() {
        when(this.stateRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(StatePreConditionException.class, () -> this.stateService.findById("SP"));
    }

    @Test
    @DisplayName("Should test find")
    public void shouldTestFind() {
        State state = new State();
        state.setId("1");
        state.setName("SP");
        when(this.stateRepository.findById(anyString()))
                .thenReturn(Optional.of(state));

        State stateFind = this.stateService.findById("SP");

        assertEquals("1", stateFind.getId());
        assertEquals("SP", stateFind.getName());
    }
}