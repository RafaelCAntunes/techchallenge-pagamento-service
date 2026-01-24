package com.lanchonete.pagamento.infra.persistence;

import com.lanchonete.pagamento.domain.Pagamento;
import com.lanchonete.pagamento.domain.PagamentoStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PagamentoEntityMapper {

    public PagamentoEntity toEntity(Pagamento pagamento) {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setPedidoId(pagamento.getPedidoId());
        entity.setValor(pagamento.getValor());
        entity.setClienteId(pagamento.getClienteId());
        entity.setStatus(pagamento.getStatus().name());
        entity.setCriadoEm(pagamento.getCriadoEm().getEpochSecond());
        entity.setAtualizadoEm(pagamento.getAtualizadoEm().getEpochSecond());
        return entity;
    }

    public Pagamento toDomain(PagamentoEntity entity) {
        return new Pagamento(
                entity.getPedidoId(),
                entity.getValor(),
                entity.getClienteId(),
                PagamentoStatus.valueOf(entity.getStatus()),
                Instant.ofEpochSecond(entity.getCriadoEm()),
                Instant.ofEpochSecond(entity.getAtualizadoEm())
        );
    }
}