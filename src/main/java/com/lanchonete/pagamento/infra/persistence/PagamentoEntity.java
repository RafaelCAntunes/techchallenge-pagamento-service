package com.lanchonete.pagamento.infra.persistence;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import java.math.BigDecimal;

@DynamoDbBean
public class PagamentoEntity {

    private String pedidoId;
    private BigDecimal valor;
    private Long clienteId;
    private String status;
    private Long criadoEm;
    private Long atualizadoEm;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pedidoId")
    public String getPedidoId() { return pedidoId; }
    public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }

    @DynamoDbAttribute("valor")
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    @DynamoDbAttribute("clienteId")
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    @DynamoDbSecondaryPartitionKey(indexNames = "StatusIndex")
    @DynamoDbAttribute("status")
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @DynamoDbSecondarySortKey(indexNames = "StatusIndex")
    @DynamoDbAttribute("criadoEm")
    public Long getCriadoEm() { return criadoEm; }
    public void setCriadoEm(Long criadoEm) { this.criadoEm = criadoEm; }

    @DynamoDbAttribute("atualizadoEm")
    public Long getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(Long atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}