resource "aws_db_subnet_group" "this" {
  name       = "phr-db-subnets"
  subnet_ids = var.subnets
}

resource "aws_db_instance" "this" {
  identifier           = "phr-db"
  engine               = "postgres"
  instance_class       = "db.t3.micro"
  allocated_storage    = 20
  username             = var.db_username
  password             = var.db_password
  db_subnet_group_name = aws_db_subnet_group.this.name
  skip_final_snapshot  = true
}
