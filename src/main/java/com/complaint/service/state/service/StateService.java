package com.complaint.service.state.service;


import com.complaint.service.state.model.State;

import java.util.Set;

public interface StateService {

    Set<State> findAll();

    State findById(String id);
}
