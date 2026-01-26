package com.lanchonete.pagamento.bdd.steps;

import com.lanchonete.pagamento.domain.Pagamento;
import com.lanchonete.pagamento.domain.PagamentoStatus;
import io.cucumber.java.pt.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RegrasPagamentoSteps {

    private Pagamento pagamento;

    @Dado("que um pagamento foi criado para o pedido {string} com valor {string} e cliente {string}")
    public void criarPagamento(String pedidoId, String valor, String clienteId) {
        this.pagamento = new Pagamento(pedidoId, new BigDecimal(valor), Long.parseLong(clienteId));
    }

    @Quando("o pagamento é aprovado")
    public void aprovarPagamento() {
        pagamento.aprovar();
    }

    @Quando("o pagamento é recusado")
    public void recusarPagamento() {
        pagamento.recusar();
    }

    @Então("o status do pagamento deve ser {string}")
    public void verificarStatus(String statusEsperado) {
        assertEquals(PagamentoStatus.valueOf(statusEsperado), pagamento.getStatus());
    }

    @Então("o pagamento deve estar aprovado")
    public void verificarAprovado() {
        assertTrue(pagamento.isAprovado());
    }

    @Então("o pagamento não deve estar aprovado")
    public void verificarNaoAprovado() {
        assertFalse(pagamento.isAprovado());
    }
}
