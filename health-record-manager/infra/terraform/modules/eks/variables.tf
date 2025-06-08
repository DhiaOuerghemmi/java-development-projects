variable "cluster_name" {
  type    = string
  default = "phr-eks-cluster"
}
variable "cluster_role_arn" {
  type = string
}
variable "subnet_ids" {
  type = list(string)
}
