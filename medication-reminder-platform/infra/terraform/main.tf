terraform {
  required_version = ">= 1.1.0"
  backend "s3" { /* as before */ }
}

provider "aws" {
  region = var.aws_region
}

data "terraform_remote_state" "vpc" {
  backend = "s3"
  config = {
    bucket = "my-org-terraform-state"
    key    = "medication-reminder/vpc.tfstate"
    region = var.aws_region
  }
}

module "iam" {
  source = "./modules/iam"
  eks_cluster_oidc_issuer = module.eks.cluster_oidc_issuer_url
  aws_region              = var.aws_region
  environment             = var.environment
}

module "eks" {
  source          = "./modules/eks"
  aws_region      = var.aws_region
  vpc_id          = data.terraform_remote_state.vpc.outputs.vpc_id
  public_subnets  = data.terraform_remote_state.vpc.outputs.public_subnet_ids
  private_subnets = data.terraform_remote_state.vpc.outputs.private_subnet_ids
  cluster_name    = "med-reminder-${var.environment}"
  node_groups = {
    default = {
      desired_capacity = 2
      max_capacity     = 4
      min_capacity     = 1
      instance_types   = ["t3.medium"]
    }
  }
}

module "rds" {
  source            = "./modules/rds"
  aws_region        = var.aws_region
  subnet_ids        = data.terraform_remote_state.vpc.outputs.private_subnet_ids
  vpc_security_group_ids = [module.eks.cluster_security_group_id]
  db_name           = "medreminder"
  username          = var.db_username
  password          = var.db_password
  multi_az          = true
  allocated_storage = 20
  instance_class    = "db.t3.micro"
  environment       = var.environment
}

# Optional: deploy Prometheus via Helm on the new cluster
module "monitoring" {
  source         = "./modules/monitoring"
  kubeconfig     = module.eks.kubeconfig
  namespace      = "monitoring"
}
