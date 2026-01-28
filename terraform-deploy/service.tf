resource "kubernetes_service" "pagamento" {
  metadata {
    name = "pagamento-service"
  }

  spec {
    selector = {
      app = "pagamento"
    }

    port {
      port        = 80
      target_port = 8080
    }

    type = "LoadBalancer"
  }
}