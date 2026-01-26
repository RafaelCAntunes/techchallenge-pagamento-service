package com.lanchonete.pagamento.bdd.steps;

import com.lanchonete.pagamento.application.ConsultarPagamentoUseCase;
import com.lanchonete.pagamento.domain.Pagamento;
import com.lanchonete.pagamento.domain.PagamentoStatus;
import com.lanchonete.pagamento.domain.PagamentoRepositoryPort;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarPagamentoSteps {

    private PagamentoRepositoryPort repositoryMock;
    private ConsultarPagamentoUseCase useCase;

    private Pagamento pagamentoConsultado;
    private Exception excecaoLancada;

    @Before
    public void setUp() {
        repositoryMock = mock(PagamentoRepositoryPort.class);
        useCase = new ConsultarPagamentoUseCase(repositoryMock);
        pagamentoConsultado = null;
        excecaoLancada = null;
    }

    @Dado("que existe um pagamento para o pedido {string} com status {string}")
    public void queExisteUmPagamentoParaOPedidoComStatus(String pedidoId, String status) {
        Pagamento pagamento = new Pagamento(
                pedidoId,
                new BigDecimal("50.00"),
                1L,
                PagamentoStatus.valueOf(status),
                Instant.now(),
                Instant.now()
        );
        when(repositoryMock.buscarPorPedidoId(pedidoId)).thenReturn(Optional.of(pagamento));
    }

    @Dado("que não existe pagamento para o pedido {string}")
    public void queNaoExistePagamentoParaOPedido(String pedidoId) {
        when(repositoryMock.buscarPorPedidoId(pedidoId)).thenReturn(Optional.empty());
    }

    @Quando("eu consulto o pagamento do pedido {string}")
    public void euConsultoOPagamentoDoPedido(String pedidoId) {
        try {
            pagamentoConsultado = useCase.executar(pedidoId);
        } catch (Exception e) {
            excecaoLancada = e;
        }
    }

    @Quando("eu verifico se o pagamento do pedido {string} foi aprovado")
    public void euVerificoSeOPagamentoFoiAprovado(String pedidoId) {
        try {
            pagamentoConsultado = useCase.executar(pedidoId);
        } catch (Exception e) {
            excecaoLancada = e;
        }
    }

    @Então("o pagamento deve ser retornado")
    public void oPagamentoDeveSerRetornado() {
        assertNotNull(pagamentoConsultado, "Pagamento não foi retornado");
    }

    @Então("o status deve ser {string}")
    public void oStatusDeveSer(String statusEsperado) {
        assertEquals(PagamentoStatus.valueOf(statusEsperado), pagamentoConsultado.getStatus());
    }

    @Então("o valor deve ser {string}")
    public void oValorDeveSer(String valorEsperado) {
        assertEquals(new BigDecimal(valorEsperado), pagamentoConsultado.getValor());
    }

    @Então("o cliente deve ser {string}")
    public void oClienteDeveSer(String clienteEsperado) {
        assertEquals(Long.parseLong(clienteEsperado), pagamentoConsultado.getClienteId());
    }

    @Então("deve lançar uma exceção informando {string}")
    public void deveLancarUmaExcecaoInformando(String mensagemEsperada) {
        assertNotNull(excecaoLancada, "Exceção não foi lançada");
        assertTrue(excecaoLancada.getMessage().contains(mensagemEsperada),
                "Mensagem esperada: " + mensagemEsperada + ", mas foi: " + excecaoLancada.getMessage());
    }

    @Então("o resultado deve ser verdadeiro")
    public void oResultadoDeveSerVerdadeiro() {
        assertNotNull(pagamentoConsultado, "Pagamento não foi consultado");
        assertTrue(pagamentoConsultado.isAprovado());
    }
}
