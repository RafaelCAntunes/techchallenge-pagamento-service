resource "kubernetes_deployment" "pagamento" {
  metadata {
    name = "pagamento"
    labels = {
      app = "pagamento"
    }
  }

  spec {
    replicas = 2

    selector {
      match_labels = {
        app = "pagamento"
      }
    }

    template {
      metadata {
        labels = {
          app = "pagamento"
        }
      }

      spec {
        container {
          name  = "pagamento"
          image = "165835313479.dkr.ecr.us-east-1.amazonaws.com/techchallenge_lanchonete-pagamento:latest"

          port {
            container_port = 8080
          }

          # DynamoDB
          env {
            name  = "AWS_REGION"
            value = var.aws_region
          }

          env {
            name  = "DYNAMODB_PAGAMENTOS_TABLE"
            value = data.terraform_remote_state.db.outputs.dynamodb_pagamentos_table_name
          }

          # URLs dos outros serviços
          env {
            name  = "PEDIDOS_SERVICE_URL"
            value = "http://pedidos-service:8080"
          }

          env {
            name  = "SPRING_PROFILES_ACTIVE"
            value = "prod"
          }

          resources {
            limits = {
              cpu    = "400m"
              memory = "512Mi"
            }
            requests = {
              cpu    = "200m"
              memory = "256Mi"
            }
          }

          liveness_probe {
            http_get {
              path = "/actuator/health/liveness"
              port = 8080
            }
            initial_delay_seconds = 60
            period_seconds        = 30
            failure_threshold     = 3
          }

          readiness_probe {
            http_get {
              path = "/actuator/health/readiness"
              port = 8080
            }
            initial_delay_seconds = 40
            period_seconds        = 10
            failure_threshold     = 3
          }
        }
      }
    }
  }
}