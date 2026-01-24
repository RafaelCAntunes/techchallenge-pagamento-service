package com.lanchonete.pagamento.domain;

import java.util.Optional;

public interface PagamentoRepositoryPort {
    Pagamento salvar(Pagamento pagamento);
    Optional<Pagamento> buscarPorPedidoId(String pedidoId);
    void atualizarStatus(String pedidoId, PagamentoStatus novoStatus);
}