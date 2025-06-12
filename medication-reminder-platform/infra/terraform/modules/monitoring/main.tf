provider "helm" {
  kubernetes {
    config_path = ""  # use in-memory kubeconfig below
    config_raw  = var.kubeconfig
  }
}

resource "kubernetes_namespace" "monitoring" {
  metadata { name = var.namespace }
}

resource "helm_release" "prometheus" {
  name       = "kube-prometheus-stack"
  repository = "https://prometheus-community.github.io/helm-charts"
  chart      = "kube-prometheus-stack"
  version    = "45.6.0"
  namespace  = var.namespace
  values     = [file("${path.module}/values.yaml")]
}
