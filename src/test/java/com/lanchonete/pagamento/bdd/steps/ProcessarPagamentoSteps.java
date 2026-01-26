package com.lanchonete.pagamento.bdd.steps;

import com.lanchonete.pagamento.application.ProcessarPagamentoUseCase;
import com.lanchonete.pagamento.domain.*;
import io.cucumber.java.pt.*;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcessarPagamentoSteps {

    private PagamentoRepositoryPort repositoryMock = mock(PagamentoRepositoryPort.class);
    private PedidoNotificacaoPort notificacaoMock = mock(PedidoNotificacaoPort.class);

    private ProcessarPagamentoUseCase useCase = new ProcessarPagamentoUseCase(repositoryMock, notificacaoMock);

    private Pagamento pagamentoProcessado;

    @Quando("eu processar o pagamento do pedido {string} com valor {string} e cliente {string}")
    public void euProcessarOPagamentoDoPedido(String pedidoId, String valor, String clienteId) {
        BigDecimal valorPagamento = new BigDecimal(valor);
        Long idCliente = Long.parseLong(clienteId);

        doAnswer(invocation -> {
            pagamentoProcessado = invocation.getArgument(0, Pagamento.class);
            return null;
        }).when(repositoryMock).salvar(any(Pagamento.class));

        useCase.executar(pedidoId, valorPagamento, idCliente);
    }

    @Então("o pagamento deve ser aprovado")
    public void oPagamentoDeveSerAprovado() {
        assertNotNull(pagamentoProcessado, "Pagamento não foi processado");
        assertTrue(pagamentoProcessado.isAprovado(), "Pagamento não está aprovado");
    }

    @Então("o pagamento deve ser recusado")
    public void oPagamentoDeveSerRecusado() {
        assertNotNull(pagamentoProcessado, "Pagamento não foi processado");
        assertFalse(pagamentoProcessado.isAprovado(), "Pagamento não está recusado");
    }

    @Então("o pedido deve ser notificado com status {string}")
    public void oPedidoDeveSerNotificadoComStatus(String statusEsperado) {
        assertNotNull(pagamentoProcessado, "Pagamento não foi processado");

        ArgumentCaptor<PagamentoStatus> statusCaptor = ArgumentCaptor.forClass(PagamentoStatus.class);
        verify(notificacaoMock).notificarPagamento(eq(pagamentoProcessado.getPedidoId()), statusCaptor.capture());

        assertEquals(PagamentoStatus.valueOf(statusEsperado), statusCaptor.getValue(),
                "Status notificado não corresponde ao esperado");
    }
}
