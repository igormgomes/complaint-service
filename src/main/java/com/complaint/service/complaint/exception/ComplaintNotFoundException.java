package com.complaint.service.complaint.exception;


public class ComplaintNotFoundException extends RuntimeException {

    public ComplaintNotFoundException() {
        super("Complaint not found");
    }

    public ComplaintNotFoundException(String message) {
        super(message);
    }
}
