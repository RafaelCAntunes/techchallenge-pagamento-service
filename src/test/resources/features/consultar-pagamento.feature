# language: pt

Funcionalidade: Consultar Pagamento
  Como sistema de lanchonete
  Quero consultar o status de um pagamento
  Para saber se um pedido foi pago

  Cenário: Consultar pagamento existente
    Dado que existe um pagamento para o pedido "123" com status "APROVADO"
    Quando eu consulto o pagamento do pedido "123"
    Então o pagamento deve ser retornado
    E o status deve ser "APROVADO"
    E o valor deve ser "50.00"
    E o cliente deve ser "1"

  Cenário: Consultar pagamento que não existe
    Dado que não existe pagamento para o pedido "999"
    Quando eu consulto o pagamento do pedido "999"
    Então deve lançar uma exceção informando "Pagamento não encontrado para pedido: 999"

  Cenário: Consultar pagamento recusado
    Dado que existe um pagamento para o pedido "124" com status "RECUSADO"
    Quando eu consulto o pagamento do pedido "124"
    Então o pagamento deve ser retornado
    E o status deve ser "RECUSADO"

  Cenário: Verificar se pagamento foi aprovado
    Dado que existe um pagamento para o pedido "125" com status "APROVADO"
    Quando eu verifico se o pagamento do pedido "125" foi aprovado
    Então o resultado deve ser verdadeiro