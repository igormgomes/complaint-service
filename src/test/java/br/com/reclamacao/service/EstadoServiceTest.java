package br.com.reclamacao.service;

import br.com.reclamacao.service.domain.repository.EstadoRepository;
import br.com.reclamacao.service.domain.service.EstadoService;
import br.com.reclamacao.service.domain.service.impl.EstadoServiceImpl;
import br.com.reclamacao.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Teste do service da estado")
public class EstadoServiceTest {

    private EstadoRepository estadoRepository;
    private EstadoService estadoService;

    @BeforeEach
    public void beforeEach() {
        this.estadoRepository = mock(EstadoRepository.class);
        this.estadoService = new EstadoServiceImpl(this.estadoRepository);
    }

    @Test
    @DisplayName("Testa que é lançada uma exception")
    public void testaQueEhLancadaUmaException() {
        when(this.estadoRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () -> this.estadoService.buscaPorId("SP"));
    }
}