# Medication Reminder Platform

_A microservices-based Spring Boot platform that schedules and sends medication reminders via email (AWS SES) and SMS (AWS SNS)._

## Overview

- **Auth-Service**: JWT-based user authentication.
- **Reminder-Service**: Scheduled jobs reading medication plans and sending notifications.
- **Infra**: AWS (EKS, RDS) configured via Terraform.
- **Platform**: Kubernetes manifests (Helm & Kustomize).
- **Monitoring**: Prometheus & Grafana setups.
- **CI/CD**: GitHub Actions for build, test, and deploy.

## Getting Started

1. Clone the repo:
   ```bash
   git clone git@github.com:<your-org>/medication-reminder-platform.git
   cd medication-reminder-platform
