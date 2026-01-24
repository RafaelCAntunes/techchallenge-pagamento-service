package com.lanchonete.pagamento.interfaces.controllers;

import com.lanchonete.pagamento.application.*;
import com.lanchonete.pagamento.domain.Pagamento;
import com.lanchonete.pagamento.interfaces.dto.PagamentoRequestDTO;
import com.lanchonete.pagamento.interfaces.dto.PagamentoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarUseCase;
    private final ConsultarPagamentoUseCase consultarUseCase;

    public PagamentoController(
            ProcessarPagamentoUseCase processarUseCase,
            ConsultarPagamentoUseCase consultarUseCase) {
        this.processarUseCase = processarUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> processar(@RequestBody PagamentoRequestDTO request) {

        processarUseCase.executar(
                request.getPedidoId().toString(),
                request.getValor(),
                request.getClienteId()
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PagamentoResponseDTO> consultar(@PathVariable String pedidoId) {
        Pagamento pagamento = consultarUseCase.executar(pedidoId);
        return ResponseEntity.ok(PagamentoResponseDTO.from(pagamento));
    }
}