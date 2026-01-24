package com.lanchonete.pagamento.config;

import com.lanchonete.pagamento.application.*;
import com.lanchonete.pagamento.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ProcessarPagamentoUseCase processarPagamentoUseCase(
            PagamentoRepositoryPort repository,
            PedidoNotificacaoPort pedidoNotificacao) {
        return new ProcessarPagamentoUseCase(repository, pedidoNotificacao);
    }

    @Bean
    public ConsultarPagamentoUseCase consultarPagamentoUseCase(
            PagamentoRepositoryPort repository) {
        return new ConsultarPagamentoUseCase(repository);
    }
}
