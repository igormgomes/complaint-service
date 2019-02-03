package com.complaint.service.handler;

import com.google.common.collect.Lists;

import java.util.List;

public class ErrorMessage {

    private List<String> errors = Lists.newArrayList();

    public ErrorMessage (String error) {
        this.errors.add(error);
    }

    public ErrorMessage(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
