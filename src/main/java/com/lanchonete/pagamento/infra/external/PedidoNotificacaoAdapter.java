package com.lanchonete.pagamento.infra.external;

import com.lanchonete.pagamento.domain.PagamentoStatus;
import com.lanchonete.pagamento.domain.PedidoNotificacaoPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PedidoNotificacaoAdapter implements PedidoNotificacaoPort {

    private final WebClient webClient;

    public PedidoNotificacaoAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${services.pedidos.url}") String pedidosUrl) {
        this.webClient = webClientBuilder
                .baseUrl(pedidosUrl)
                .build();
    }

    @Override
    public void notificarPagamento(String pedidoId, PagamentoStatus status) {

        webClient.post()
                .uri("/callback/{id}/pagamento", pedidoId)
                .bodyValue(status)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }
}