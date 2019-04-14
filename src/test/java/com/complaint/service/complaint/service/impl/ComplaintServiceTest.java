package com.complaint.service.complaint.service.impl;

import com.complaint.service.complaint.exception.ComplaintNotFoundException;
import com.complaint.service.complaint.exception.ComplaintPreConditionException;
import com.complaint.service.complaint.model.Complaint;
import com.complaint.service.complaint.repository.ComplaintRepository;
import com.complaint.service.complaint.service.ComplaintService;
import com.complaint.service.state.model.State;
import com.complaint.service.state.service.StateService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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

@DisplayName("Complaint service test")
public class ComplaintServiceTest {

    private ComplaintRepository complaintRepository;
    private StateService stateService;

    private ComplaintService complaintService;

    @BeforeEach
    public void beforeEach () {
        this.complaintRepository = mock(ComplaintRepository.class);
        this.stateService = mock(StateService.class);
        this.complaintService = new ComplaintServiceImpl(this.complaintRepository, this.stateService);
    }

    @Test
    @DisplayName("Should test save")
    public void shouldTestSave() {
        when(this.stateService.findById(anyString()))
                .thenReturn(getState());
        when(this.complaintRepository.save(any()))
                .thenReturn(getComplaint());

        this.complaintService.save(getComplaint());

        verify(this.complaintRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("Should throw ComplaintNotFoundException in findAll all")
    public void shouldThrowComplaintNotFoundExceptionInFindAll() {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ComplaintNotFoundException.class, () -> this.complaintService.find("", ""));
    }

    @Test
    @DisplayName("Should throw ComplaintNotFoundException in find with state id and company name")
    public void shouldThrowComplaintNotFoundExceptionInFindWithStateIdAndCompanyName() {
        when(this.complaintRepository.findAllByStateAndCompanyName(any(), anyString()))
                .thenReturn(Sets.newHashSet());

        assertThrows(ComplaintNotFoundException.class, () -> this.complaintService.find("1", "reclame"));
    }

    @Test
    @DisplayName("Should test findAll all")
    public void shouldTestFindAll () {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList(getComplaint()));

        Set<Complaint> complaints = this.complaintService.find("", "");

        assertThat(complaints.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Should test find with state id and company name")
    public void shouldTestFindAllWithStateIdAndCompanyName() {
        when(this.stateService.findById(anyString()))
                .thenReturn(getState());
        when(this.complaintRepository.findAllByStateAndCompanyName(any(), anyString()))
                .thenReturn(Sets.newHashSet(getComplaint()));

        Set<Complaint> complaints = this.complaintService.find("1", "reclame");

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
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(getComplaint()));

        this.complaintService.delete("1");

        verify(this.complaintRepository, atLeastOnce()).delete(any());
    }

    @Test
    @DisplayName("Should ComplaintPreConditionException in update")
    public void shouldThrowComplaintPreConditionExceptionInUpdate() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(ComplaintPreConditionException.class, () -> this.complaintService.update(Complaint.builder().build(), "1"));

        verify(this.complaintRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should test update")
    public void shouldTestUpdate() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(getComplaint()));
        when(this.stateService.findById(anyString()))
                .thenReturn(getState());

        this.complaintService.update(getComplaint(), "2");

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
    @DisplayName("Should test find by id")
    public void shouldTestFindById() {
        when(this.complaintRepository.findById(anyString()))
                .thenReturn(Optional.of(getComplaint()));

        Complaint complaint = this.complaintService.findbyId(anyString());

        assertNotNull(complaint);
        assertEquals("1", complaint.getId());
        assertEquals("title", complaint.getTitle());
        assertEquals("description", complaint.getDescription());
        assertEquals("company name", complaint.getCompanyName());
    }

    private Complaint getComplaint() {
        return Complaint.builder()
                .id("1")
                .title("title")
                .description("description")
                .state(State.builder().id("1").build())
                .companyName("company name")
                .build();
    }

    private State getState() {
        return State.builder()
                .id("1")
                .name("SP")
                .build();
    }
}
