package com.lanchonete.pagamento.infra.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoCallbackDTO {
    private String pedidoId;
    private String status;
}