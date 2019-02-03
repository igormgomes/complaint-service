package com.complaint.service.state.service.impl;

import com.complaint.service.state.exception.StateNotFoundException;
import com.complaint.service.state.exception.StatePreConditionException;
import com.complaint.service.state.model.State;
import com.complaint.service.state.repository.StateRepository;
import com.complaint.service.state.service.StateService;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Set<State> findAll() {
        List<State> states = this.stateRepository.findAll();
        if(states.isEmpty()){
            throw new StateNotFoundException();
        }
        return Sets.newHashSet(states);
    }

    @Override
    public State findById(String id) {
        return this.stateRepository.findById(id)
                .orElseThrow(() -> new StatePreConditionException(String.format("%s%s%s", "State id ", id, " not found")));
    }
}