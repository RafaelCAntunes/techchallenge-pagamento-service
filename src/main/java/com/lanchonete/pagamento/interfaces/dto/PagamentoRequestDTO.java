package com.lanchonete.pagamento.interfaces.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequestDTO {

    @NotNull(message = "pedidoId é obrigatório")
    private Long pedidoId;

    @NotNull(message = "valor é obrigatório")
    @Positive(message = "valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "clienteId é obrigatório")
    private Long clienteId;
}
