package com.lanchonete.pagamento.domain;

public interface PedidoNotificacaoPort {
    void notificarPagamento(String pedidoId, PagamentoStatus status);
}