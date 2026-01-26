# language: pt

Funcionalidade: Status do Pagamento
  Como sistema de lanchonete
  Quero alterar e consultar o status de um pagamento
  Para saber se um pedido foi aprovado ou recusado

  Cenário: Pagamento criado deve ter status AGUARDANDO
    Dado que um pagamento foi criado para o pedido "201" com valor "100.00" e cliente "1"
    Então o status do pagamento deve ser "AGUARDANDO"
    E o pagamento não deve estar aprovado

  Cenário: Aprovar pagamento
    Dado que um pagamento foi criado para o pedido "202" com valor "150.00" e cliente "2"
    Quando o pagamento é aprovado
    Então o status do pagamento deve ser "APROVADO"
    E o pagamento deve estar aprovado

  Cenário: Recusar pagamento
    Dado que um pagamento foi criado para o pedido "203" com valor "200.00" e cliente "3"
    Quando o pagamento é recusado
    Então o status do pagamento deve ser "RECUSADO"
    E o pagamento não deve estar aprovado
