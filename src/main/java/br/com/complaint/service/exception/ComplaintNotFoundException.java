package br.com.complaint.service.exception;

public class ComplaintNotFoundException extends NotFoundException {

    public ComplaintNotFoundException() {
        super("Complaint not found");
    }

    public ComplaintNotFoundException(String message) {
        super(message);
    }
}
