package com.lanchonete.pagamento.interfaces.dto;

import com.lanchonete.pagamento.domain.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponseDTO {

    private String pedidoId;
    private BigDecimal valor;
    private String status;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public static PagamentoResponseDTO from(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getPedidoId(),
                pagamento.getValor(),
                pagamento.getStatus().name(),
                pagamento.getCriadoEm(),
                pagamento.getAtualizadoEm()
        );
    }
}
