# language: pt

Funcionalidade: Processar Pagamento
  Como sistema de lanchonete
  Quero processar o pagamento de um pedido
  Para atualizar o status e notificar o pedido

  Cenário: Processar pagamento aprovado
    Quando eu processar o pagamento do pedido "101" com valor "50.00" e cliente "1"
    Então o pagamento deve ser aprovado
    E o pedido deve ser notificado com status "APROVADO"

  Cenário: Processar pagamento recusado
    Quando eu processar o pagamento do pedido "102" com valor "0.00" e cliente "2"
    Então o pagamento deve ser recusado
    E o pedido deve ser notificado com status "RECUSADO"
