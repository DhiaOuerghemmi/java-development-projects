variable "aws_region"      { type = string }
variable "vpc_id"          { type = string }
variable "public_subnets"  { type = list(string) }
variable "private_subnets" { type = list(string) }
variable "cluster_name"    { type = string }
variable "node_groups"     { type = map(object({
  desired_capacity = number
  max_capacity     = number
  min_capacity     = number
  instance_types   = list(string)
})) }
