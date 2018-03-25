package br.com.complaint.service.domain.service;


import br.com.complaint.service.domain.model.State;

import java.util.Set;

public interface StateService {

    Set<State> find();

    State findById(String id);
}
