package com.lanchonete.pagamento.application;

import com.lanchonete.pagamento.domain.Pagamento;
import com.lanchonete.pagamento.domain.PagamentoRepositoryPort;

public class ConsultarPagamentoUseCase {

    private final PagamentoRepositoryPort repository;

    public ConsultarPagamentoUseCase(PagamentoRepositoryPort repository) {
        this.repository = repository;
    }

    public Pagamento executar(String pedidoId) {
        return repository.buscarPorPedidoId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pagamento não encontrado para pedido: " + pedidoId));
    }
}
