package com.lanchonete.pagamento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento{

    private String pedidoId;
    private BigDecimal valor;
    private Long clienteId;
    private PagamentoStatus status;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public Pagamento(String pedidoId, BigDecimal valor, Long clienteId) {
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.clienteId = clienteId;
        this.status = PagamentoStatus.AGUARDANDO;
        this.criadoEm = Instant.now();
        this.atualizadoEm = Instant.now();
    }

    public void aprovar() {
        this.status = PagamentoStatus.APROVADO;
        this.atualizadoEm = Instant.now();
    }

    public void recusar() {
        this.status = PagamentoStatus.RECUSADO;
        this.atualizadoEm = Instant.now();
    }

    public boolean isAprovado() {
        return this.status == PagamentoStatus.APROVADO;
    }
}
