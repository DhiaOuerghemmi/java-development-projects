terraform {
  backend "s3" {
    bucket         = "my-org-terraform-state"
    key            = "medication-reminder/terraform.tfstate"
    region         = "us-east-1"
    use_lockfile = "terraform-locks"
    encrypt        = true
  }
}
