data "aws_iam_openid_connect_provider" "eks" {
  url = var.eks_cluster_oidc_issuer
}

resource "aws_iam_role" "ses_sns_irsa" {
  name = "irsa-ses-sns-${var.environment}"
  assume_role_policy = data.aws_iam_openid_connect_provider.eks.document
}

resource "aws_iam_policy" "ses_sns" {
  name        = "ses-sns-policy-${var.environment}"
  description = "Allow SES/SNS publish"
  policy      = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action   = ["ses:SendEmail","sns:Publish"]
        Effect   = "Allow"
        Resource = "*"
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "ses_sns_attach" {
  role       = aws_iam_role.ses_sns_irsa.name
  policy_arn = aws_iam_policy.ses_sns.arn
}

output "irsa_role_arn" { value = aws_iam_role.ses_sns_irsa.arn }
