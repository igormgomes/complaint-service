package com.complaint.service.util;

import com.complaint.service.state.model.State;
import com.complaint.service.state.repository.StateRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;

import static org.mockito.Mockito.*;

@DisplayName("State runner test")
public class StateRunnerTest {

    private StateRepository stateRepository;

    private StateRunner stateRunner;

    @BeforeEach
    public void beforeEach() {
        this.stateRepository = mock(StateRepository.class);
        this.stateRunner = new StateRunner(this.stateRepository);
    }

    @Test
    @DisplayName("Should not state save")
    public void shouldNotSaveState() throws Exception {
        when(this.stateRepository.findAll())
                .thenReturn(Lists.newArrayList(getState()));

        String[] args = {"run"};
        this.stateRunner.run(new DefaultApplicationArguments(args));

        verify(this.stateRepository, never()).saveAll(any());
    }

    @Test
    @DisplayName("Should state save")
    public void shouldSaveState() throws Exception {
        when(this.stateRepository.findAll())
                .thenReturn(Lists.newArrayList());

        String[] args = {"run"};
        this.stateRunner.run(new DefaultApplicationArguments(args));

        verify(this.stateRepository, atLeastOnce()).saveAll(any());
    }

    private State getState() {
        return State.builder()
                .id("1")
                .name("SP")
                .build();
    }
}
