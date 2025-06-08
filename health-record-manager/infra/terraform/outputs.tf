output "eks_cluster_name" {
  description = "EKS cluster name"
  value       = module.eks.cluster_name
}
output "rds_endpoint" {
  description = "RDS instance endpoint"
  value       = module.rds.endpoint
}
