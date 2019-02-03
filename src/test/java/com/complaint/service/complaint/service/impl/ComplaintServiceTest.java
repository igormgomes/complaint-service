package com.complaint.service.complaint.service.impl;

import com.complaint.service.complaint.exception.ComplaintNotFoundException;
import com.complaint.service.complaint.exception.ComplaintPreConditionException;
import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.complaint.repository.ComplaintRepository;
import com.complaint.service.complaint.service.ComplaintService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Test of the complaint service")
public class ComplaintServiceTest {

    private ComplaintRepository complaintRepository;
    private ComplaintService complaintService;

    @BeforeEach
    public void beforeEach () {
        this.complaintRepository = mock(ComplaintRepository.class);
        this.complaintService = new ComplaintServiceImpl(this.complaintRepository);
    }

    @Test
    @DisplayName("Should throw ComplaintNotFoundException in findAll all")
    public void shouldThrowComplaintNotFoundExceptionInFindAll() {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ComplaintNotFoundException.class, () -> this.complaintService.find());
    }

    @Test
    @DisplayName("Should test findAll all")
    public void shouldTestFindAll () {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList(new Complaint()));

        Set<Complaint> complaints = this.complaintService.find();

        assertThat(complaints.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Should throw ComplaintPreConditionException in delete")
    public void shouldThrowComplaintPreConditionExceptionInDelete() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(ComplaintPreConditionException.class, () ->  this.complaintService.delete("1"));

        verify(this.complaintRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Should test delete")
    public void shouldTestDelete() {
        Complaint complaint = new Complaint();
        complaint.setId("1");
        complaint.setCompanyName("American");
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(complaint));

        this.complaintService.delete("1");

        verify(this.complaintRepository, atLeastOnce()).delete(any());
    }

    @Test
    @DisplayName("Should ComplaintPreConditionException in update")
    public void shouldThrowComplaintPreConditionExceptionInUpdate() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(ComplaintPreConditionException.class, () ->  this.complaintService.update(new Complaint(), "1"));

        verify(this.complaintRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should test update")
    public void shouldTestUpdate() {
        Complaint complaint = new Complaint();
        complaint.setId("1");
        complaint.setCompanyName("American");
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(complaint));

        this.complaintService.update(new Complaint(), "2");

        verify(this.complaintRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("Should throw not found exception because id to findAll is invalid")
    public void shouldThrowComplaintNotFoundExceptionInFindById() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(ComplaintNotFoundException.class, () -> this.complaintService.findbyId("1"));
    }

    @Test
    @DisplayName("Should test findAll")
    public void shouldTestFind() {
        Complaint complaint = new Complaint();
        complaint.setId("1");
        complaint.setCompanyName("American");
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(complaint));

        Complaint complaintFind = this.complaintService.findbyId(anyString());

        assertNotNull(complaintFind);
        assertEquals("1", complaintFind.getId());
        assertEquals("American", complaintFind.getCompanyName());
    }
}
