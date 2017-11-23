package br.com.reclamacao.service;

import br.com.reclamacao.service.domain.model.Reclamacao;
import br.com.reclamacao.service.domain.repository.EmpresaRepository;
import br.com.reclamacao.service.domain.repository.ReclamacaoRepository;
import br.com.reclamacao.service.domain.service.ReclamacaoService;
import br.com.reclamacao.service.domain.service.impl.ReclamacaoServiceImpl;
import br.com.reclamacao.service.exception.NotFoundException;
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

@DisplayName("Teste do service da reclamação")
public class ReclamacaoServiceTest {

    private ReclamacaoRepository reclamacaoRepository;
    private EmpresaRepository empresaRepository;
    private ReclamacaoService reclamacaoService;

    @BeforeEach
    public void beforeEach () {
        this.reclamacaoRepository = mock(ReclamacaoRepository.class);
        this.empresaRepository = mock(EmpresaRepository.class);
        this.reclamacaoService = new ReclamacaoServiceImpl(this.reclamacaoRepository, this.empresaRepository);
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso nao volte elementos na lista")
    public void testaQueEhLancadaUmaExceptionCasoNaoVolteElementosNaLista() {
        when(this.reclamacaoRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(NotFoundException.class, () -> this.reclamacaoService.busca());
    }

    @Test
    @DisplayName("Testa que a lista é retornada com elementos")
    public void testaQueAListaEhRetornadaComElementos () {
        when(this.reclamacaoRepository.findAll())
                .thenReturn(Lists.newArrayList(new Reclamacao()));

        Set<Reclamacao> reclamacoes = this.reclamacaoService.busca();

        assertThat(reclamacoes.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso o id para deleção seja inválido")
    public void testaQueEhLancadaUmaExceptionCasoOIdParaDelecaoSejaInvalido() {
        when(this.reclamacaoRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () ->  this.reclamacaoService.deleta("1"));
    }

    @Test
    @DisplayName("Testa que a deleção é feita por um id válido")
    public void testaQueADelecaoEhFeitaPorUmIdValido() {
        when(this.reclamacaoRepository.findOne(anyString()))
                .thenReturn(new Reclamacao());

        this.reclamacaoService.deleta("1");
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso o id para edição seja inválido")
    public void testaQueEhLancadaUmaExceptionCasoOIdParaEdicaoSejaInvalido() {
        when(this.reclamacaoRepository.findOne(""))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () ->  this.reclamacaoService.edita(new Reclamacao(), "1"));
    }

    @Test
    @DisplayName("Testa que a edição é feita por um id válido")
    public void testaQueAEdicaoEhFeitaPorUmIdValido() {
        when(this.reclamacaoRepository.findOne(""))
                .thenReturn(new Reclamacao());

        this.reclamacaoService.edita(new Reclamacao(), "");
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso a busca seja por um id inválido")
    public void testaQueEhLancadaUmaExceptionCasoABuscaSejaPorUmIdInvalido() {
        when(this.reclamacaoRepository.findOne(anyString()))
                .thenReturn(null);

        assertThrows(NotFoundException.class, () -> this.reclamacaoService.buscaPorId("1"));
    }

    @Test
    @DisplayName("Testa que é retornada uma reclamação por um id válido")
    public void testaQueEhRetornadaUmaReclamacaoPoUmIdValido() {
        when(this.reclamacaoRepository.findOne(anyString()))
                .thenReturn(new Reclamacao());

        Reclamacao reclamacao = this.reclamacaoService.buscaPorId(anyString());

        assertNotNull(reclamacao);
    }

    @Test
    @DisplayName("Testa que é lançada uma exception caso nao volte elementos na lista")
    public void testaQueEhLancadaUmaExceptionCasoNaoVolteElementosNaListaNaBuscaPorEstadoEEmpresa() {
        when(this.reclamacaoRepository.findByEmpresa_IdAndAndEstado_Id(anyString(), anyString()))
                .thenReturn(Sets.newHashSet());

        assertThrows(NotFoundException.class, () -> this.reclamacaoService.buscaPorEmpresaEstado("1", "2"));
    }
}
