package com.complaint.service.state.exception;

public class StateNotFoundException extends RuntimeException {

    public StateNotFoundException() {
        super("State not found");
    }
}
