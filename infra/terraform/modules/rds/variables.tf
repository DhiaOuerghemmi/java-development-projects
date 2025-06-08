variable "subnets" {
  type = list(string)
}
variable "db_username" {
  type = string
}
variable "db_password" {
  type      = string
  sensitive = true
}
