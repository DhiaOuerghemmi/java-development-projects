variable "aws_region" {
  description = "AWS region"
  type        = string
}

# Include the VPC module
module "vpc" {
  source       = "./modules/vpc"
  aws_region   = var.aws_region
  cidr_block   = "10.0.0.0/16"
  public_subnets  = ["10.0.1.0/24", "10.0.2.0/24"]
  private_subnets = ["10.0.11.0/24", "10.0.12.0/24"]
  tags = {
    Project = "med-reminder"
    Env     = var.environment
  }
}

output "vpc_id" {
  value = module.vpc.vpc_id
}
