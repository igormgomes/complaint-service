package com.complaint.service.util;

import com.complaint.service.state.model.State;
import com.complaint.service.state.repository.StateRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StateRunner implements ApplicationRunner {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(stateRepository.findAll().isEmpty()){
            State ac = new State();
            ac.setName("AC");

            State rs = new State();
            rs.setName("RS");

            State sp = new State();
            sp.setName("SP");

            State to = new State();
            to.setName("TO");

            this.stateRepository.saveAll(Lists.newArrayList(ac, rs, sp, to));
        }
    }
}
