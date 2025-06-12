variable "aws_region"            { type = string }
variable "subnet_ids"            { type = list(string) }
variable "vpc_security_group_ids" { type = list(string) }
variable "db_name"               { type = string }
variable "username"              { type = string }
variable "password"              { type = string, sensitive = true }
variable "multi_az"              { type = bool, default = false }
variable "allocated_storage"     { type = number }
variable "instance_class"        { type = string }
variable "environment"           { type = string }
