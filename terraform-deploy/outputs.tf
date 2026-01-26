output "service_hostname" {
  value = try(
    kubernetes_service.pagamento.status[0].load_balancer[0].ingress[0].hostname,
    "pending"
  )
  description = "O hostname público do LoadBalancer. Pode demorar alguns minutos para aparecer após o apply."
}

output "deployment_info" {
  value = {

    pagamento = {
      name     = kubernetes_deployment.pagamento.metadata[0].name
      replicas = kubernetes_deployment.pagamento.spec[0].replicas
    }

  }
  description = "Informações do deployment"
}