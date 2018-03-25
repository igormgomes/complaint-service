package br.com.complaint.service;

import br.com.complaint.service.domain.model.Complaint;
import br.com.complaint.service.domain.repository.CompanyRepository;
import br.com.complaint.service.domain.repository.ComplaintRepository;
import br.com.complaint.service.domain.service.ComplaintService;
import br.com.complaint.service.domain.service.impl.ComplaintServiceImpl;
import br.com.complaint.service.exception.NotFoundException;
import com.google.common.collect.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Test of the complaint service")
public class ComplaintServiceTest {

    private ComplaintRepository complaintRepository;
    private CompanyRepository companyRepository;
    private ComplaintService complaintService;

    @BeforeEach
    public void beforeEach () {
        this.complaintRepository = mock(ComplaintRepository.class);
        this.companyRepository = mock(CompanyRepository.class);
        this.complaintService = new ComplaintServiceImpl(this.complaintRepository, this.companyRepository);
    }

    @Test
    @DisplayName("Should Throw NotFoundException Because List Is Empty")
    public void shouldThrowNotFoundExceptionBecauseListIsEmpty() {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(NotFoundException.class, () -> this.complaintService.find());
    }

    @Test
    @DisplayName("Should test of the list contains elements")
    public void shouldTesOfTheListContainsElements () {
        when(this.complaintRepository.findAll())
                .thenReturn(Lists.newArrayList(new Complaint()));

        Set<Complaint> complaints = this.complaintService.find();

        assertThat(complaints.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Should throw not nound exception because id to delete is invalid")
    public void shouldThrowNotFoundExceptionBecauseIdToDeleteIsInvalid() {
        when(this.complaintRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () ->  this.complaintService.delete("1"));
    }

    @Test
    @DisplayName("Should test delete is ok")
    public void shouldTestDeleteIsOk() {
        when(this.complaintRepository.findOne(anyString()))
                .thenReturn(new Complaint());

        this.complaintService.delete("1");
    }

    @Test
    @DisplayName("Should throw not found exception because id to update is invalid")
    public void shouldThrowNotFoundExceptionBecauseIdForUpdateIsInvalid() {
        when(this.complaintRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () ->  this.complaintService.update(new Complaint(), "1"));
    }

    @Test
    @DisplayName("Should test update is opk")
    public void shouldTestUpdateIsOk() {
        when(this.complaintRepository.findOne(""))
                .thenReturn(new Complaint());

        this.complaintService.update(new Complaint(), "");
    }

    @Test
    @DisplayName("Should throw not found exception because id to find is invalid")
    public void shouldThrowNotFoundExceptionBecauseIdTorFindIsInvalid() {
        when(this.complaintRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () -> this.complaintService.findbyId("1"));
    }

    @Test
    @DisplayName("Should test find is ok")
    public void shouldTestFindIsOk() {
        when(this.complaintRepository.findOne(anyString()))
                .thenReturn(new Complaint());

        Complaint complaint = this.complaintService.findbyId(anyString());

        assertNotNull(complaint);
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso nao volte elementos na lista")
    public void testaQueEhLancadaUmaExceptionCasoNaoVolteElementosNaListaNaBuscaPorEstadoEEmpresa() {
        when(this.complaintRepository.findByCompany_IdAndAndState_Id(anyString(), anyString()))
                .thenReturn(Sets.newHashSet());

        assertThrows(NotFoundException.class, () -> this.complaintService.findByCompanyIdAndStateId("1", "2"));
    }
}
