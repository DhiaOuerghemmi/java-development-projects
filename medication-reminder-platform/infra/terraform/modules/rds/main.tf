resource "aws_db_subnet_group" "this" {
  name       = "db-subnets-${var.environment}"
  subnet_ids = var.subnet_ids
  tags = {
    Name        = "db-subnets-${var.environment}"
    Environment = var.environment
  }
}

resource "aws_db_instance" "this" {
  identifier         = "med-reminder-${var.environment}"
  engine             = "mysql"
  engine_version     = "8.0"
  instance_class     = var.instance_class
  allocated_storage  = var.allocated_storage
  username           = var.username
  password           = var.password
  db_subnet_group_name = aws_db_subnet_group.this.name
  vpc_security_group_ids = var.vpc_security_group_ids
  multi_az           = var.multi_az
  skip_final_snapshot = false
  deletion_protection = false
  publicly_accessible = false

  tags = {
    Environment = var.environment
    Project     = "med-reminder"
  }
}
