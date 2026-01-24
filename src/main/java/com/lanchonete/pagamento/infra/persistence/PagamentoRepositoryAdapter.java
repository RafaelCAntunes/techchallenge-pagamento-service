package com.lanchonete.pagamento.infra.persistence;

import com.lanchonete.pagamento.domain.*;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.*;

import java.util.Optional;

@Component
public class PagamentoRepositoryAdapter implements PagamentoRepositoryPort {

    private final DynamoDbTable<PagamentoEntity> table;
    private final PagamentoEntityMapper mapper;

    public PagamentoRepositoryAdapter(
            DynamoDbEnhancedClient enhancedClient,
            PagamentoEntityMapper mapper) {
        this.table = enhancedClient.table(
                "techchallenge-pagamentos",
                TableSchema.fromBean(PagamentoEntity.class)
        );
        this.mapper = mapper;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        PagamentoEntity entity = mapper.toEntity(pagamento);
        table.putItem(entity);
        return pagamento;
    }

    @Override
    public Optional<Pagamento> buscarPorPedidoId(String pedidoId) {
        Key key = Key.builder()
                .partitionValue(pedidoId)
                .build();

        PagamentoEntity entity = table.getItem(key);
        return Optional.ofNullable(entity)
                .map(mapper::toDomain);
    }

    @Override
    public void atualizarStatus(String pedidoId, PagamentoStatus novoStatus) {
        buscarPorPedidoId(pedidoId).ifPresent(pagamento -> {
            if (novoStatus == PagamentoStatus.APROVADO) {
                pagamento.aprovar();
            } else if (novoStatus == PagamentoStatus.RECUSADO) {
                pagamento.recusar();
            }
            salvar(pagamento);
        });
    }
}
