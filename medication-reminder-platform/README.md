# 💊 Medication Reminder Service

_A Spring Boot microservice that automatically sends medication reminders via Email & SMS—built for reliability, scalability, and observability in the cloud._

---

## 🌍 1. Real-World Problem

Many patients—especially the elderly or those with complex regimens—**forget critical medications** on time. This non-adherence can lead to:

- 🏥 Increased hospital admissions  
- 💸 Higher healthcare costs  
- 🤒 Poor health outcomes  

---

## ⚠️ 2. Why Is This a Concern?

| **Impact Area**         | **Consequence**                                                    |
|-------------------------|--------------------------------------------------------------------|
| 🩺 Patient Health       | Missed doses risk complications & prolonged recovery               |
| 🏨 Healthcare System    | Unplanned readmissions strain limited resources                   |
| 👪 Caregiver Burden     | Families must constantly remind or monitor dosing                 |
| 💰 Costs                | Non-adherence drives up pharma & inpatient care expenses           |

---

## 🔍 3. Existing Solutions

| **Solution**              | **👍 Pros**                          | **👎 Cons**                                     |
|---------------------------|-------------------------------------|------------------------------------------------|
| Manual Reminders          | Low cost; personal touch            | Unreliable; depends on caregiver availability  |
| Smartphone Apps           | Push notifications; user control    | Requires tech literacy; can be ignored         |
| Smart Pill Dispensers     | Automated lock/unlock schedules     | Expensive; limited remote monitoring           |
| EMR-Integrated Alerts     | Centralized within hospital systems | Complex integration; not patient-facing        |

---

## 🚀 4. Proposed Solution

A **cloud-native**, **microservices**-based platform that:

- 🔄 **Automates** dose-time checks & notification dispatch  
- 📧 **Delivers** reminders via AWS SES (Email) & SNS (SMS)  
- 📝 **Logs** every attempt for audit, retry & reporting  
- ⚙️ **Scales** seamlessly on Kubernetes (AWS EKS)  
- 📊 **Monitors** health & performance with Prometheus & Grafana  

**Problems Solved:**  
- ✅ Eliminates manual, error-prone reminders  
- ✅ Ensures high deliverability with retry logic  
- ✅ Provides observability into reminder success/failure  
- ✅ Simplifies deployment via IaC & CI/CD  

---

## 🛠️ 5. Features & Technologies

| **Feature**                       | **Technology**                                       |
|-----------------------------------|------------------------------------------------------|
| ⏰ Scheduler                       | Spring Boot Scheduler (@Scheduled)                   |
| 🌐 REST API                       | Spring Web MVC, Spring Data JPA                      |
| 🗄️ Database                       | MySQL on AWS RDS (Terraform-provisioned)             |
| 📧 Email Notifications            | AWS SES                                              |
| 📱 SMS Notifications              | AWS SNS                                              |
| 🐳 Containerization               | Docker (multi-stage)                                 |
| ☸️ Kubernetes Orchestration        | AWS EKS, Helm Charts, Kustomize Overlays             |
| 🔄 CI/CD Pipeline                 | GitHub Actions (Build → Test → Docker Push → Deploy) |
| 📜 Infrastructure as Code (IaC)   | Terraform (VPC, EKS, RDS, IAM)                       |
| 📈 Observability                  | Micrometer, Prometheus, Grafana Dashboards           |
| 🔒 Security & Compliance          | IAM Roles, SSL/TLS, DB Encryption at Rest            |
| 📝 Logging & Auditing             | JSON logs to CloudWatch; `reminder_log` table        |

---
