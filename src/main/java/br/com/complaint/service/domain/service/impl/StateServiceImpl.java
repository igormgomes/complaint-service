package br.com.complaint.service.domain.service.impl;

import br.com.complaint.service.domain.model.State;
import br.com.complaint.service.domain.repository.StateRepository;
import br.com.complaint.service.domain.service.StateService;
import br.com.complaint.service.exception.StateNotFoundException;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Set<State> find() {
        return Sets.newHashSet(this.stateRepository.findAll());
    }

    @Override
    public State findById(String id) {
        return Optional.ofNullable(this.stateRepository.findOne(id))
                .orElseThrow(() -> new StateNotFoundException
                        (String.format("%s%s%s", "State of the ", id, " not found")));
    }
}