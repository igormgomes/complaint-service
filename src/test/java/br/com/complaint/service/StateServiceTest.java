package br.com.complaint.service;

import br.com.complaint.service.domain.repository.StateRepository;
import br.com.complaint.service.domain.service.StateService;
import br.com.complaint.service.domain.service.impl.StateServiceImpl;
import br.com.complaint.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Teste do service da estado")
public class StateServiceTest {

    private StateRepository stateRepository;
    private StateService stateService;

    @BeforeEach
    public void beforeEach() {
        this.stateRepository = mock(StateRepository.class);
        this.stateService = new StateServiceImpl(this.stateRepository);
    }

    @Test
    @DisplayName("Should throw not found exception because Id is invalid")
    public void shouldThrowNotFoundExceptionBecauseIdIsInvalid() {
        when(this.stateRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () -> this.stateService.findById("SP"));
    }
}