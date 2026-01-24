package com.lanchonete.pagamento.application;

import com.lanchonete.pagamento.domain.*;

import java.math.BigDecimal;

public class ProcessarPagamentoUseCase {

    private final PagamentoRepositoryPort repository;
    private final PedidoNotificacaoPort pedidoNotificacao;

    public ProcessarPagamentoUseCase(
            PagamentoRepositoryPort repository,
            PedidoNotificacaoPort pedidoNotificacao) {
        this.repository = repository;
        this.pedidoNotificacao = pedidoNotificacao;
    }

    public void executar(String pedidoId, BigDecimal valor, Long clienteId) {

        Pagamento pagamento = new Pagamento(pedidoId, valor, clienteId);
        repository.salvar(pagamento);

        boolean aprovado = simularGatewayPagamento(valor);

        if (aprovado) {
            pagamento.aprovar();
        } else {
            pagamento.recusar();
        }
        repository.salvar(pagamento);

        pedidoNotificacao.notificarPagamento(pedidoId, pagamento.getStatus());

    }

    private boolean simularGatewayPagamento(BigDecimal valor) {
        // sempre aprova valores > 0 só para simular uma interface com api de pagamento
        return valor != null && valor.compareTo(BigDecimal.ZERO) > 0;
    }
}