package com.complaint.service.complaint.exception;

public class ComplaintPreConditionException extends RuntimeException {

    public ComplaintPreConditionException() {
        super("Complaint not found");
    }

    public ComplaintPreConditionException(String message) {
        super(message);
    }
}
