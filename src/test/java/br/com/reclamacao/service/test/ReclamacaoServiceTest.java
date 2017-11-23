package br.com.reclamacao.service.test;


import br.com.reclamacao.service.domain.repository.EmpresaRepository;
import br.com.reclamacao.service.domain.repository.ReclamacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.mock;

@DisplayName("Teste do service da campanha")
public class ReclamacaoServiceTest {

    private ReclamacaoRepository reclamacaoRepository;
    private EmpresaRepository empresaRepository;

    @BeforeEach
    public void beforeEach () {
        this.reclamacaoRepository = mock(ReclamacaoRepository.class);
        this.empresaRepository = mock(EmpresaRepository.class);
    }



}
