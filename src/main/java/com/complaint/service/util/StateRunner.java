package com.complaint.service.util;

import com.complaint.service.state.model.State;
import com.complaint.service.state.repository.StateRepository;
import com.google.common.collect.Lists;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRunner implements ApplicationRunner {

    private final StateRepository stateRepository;

    public StateRunner(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<State> states = this.stateRepository.findAll();
        if (states.isEmpty()) {
            State ac = State.builder()
                    .name("AC")
                    .build();

            State rs = State.builder()
                    .name("RS")
                    .build();

            State sp = State.builder()
                    .name("SP")
                    .build();

            State to = State.builder()
                    .name("TO")
                    .build();

            this.stateRepository.saveAll(Lists.newArrayList(ac, rs, sp, to));
        }
    }
}
