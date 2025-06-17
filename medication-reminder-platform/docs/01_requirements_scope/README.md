# Functional Requirements

1. **User & Plan Management**
   - **Create/Read/Update/Delete** medication plans via a simple REST API
   - Associate each plan with patient contact info (email, phone number)
2. **Scheduler & Notification**
   - **Cron‑based scheduler** to poll upcoming doses at configurable intervals
   - For each due dose, generate a reminder message containing:
     - Patient name
     - Medication name & dosage
     - Scheduled time
   - **Send email** via AWS SES
   - **Send SMS** via AWS SNS
   - Persist each send attempt (status, timestamp) in MySQL
3. **Retry & Failure Handling**
   - On failure (SES/SNS API error), **retry** up to 3 times with exponential back‑off
   - Mark as **“failed”** after retries and expose via API
4. **Health & Admin Endpoints**
   - `/health` (liveness) and `/ready` (readiness) for Kubernetes probes
   - `/metrics` endpoint exposing Prometheus metrics:
     - `reminders_total` (count)
     - `reminder_failures_total`
     - `notification_latency_seconds`
5. **Configuration & Profiles**
   - **`application.yml`** with `dev` and `prod` profiles
   - All sensitive credentials injected via environment variables or IAM roles

---

# Non‑Functional Requirements

| Category            | Requirement                                                               |
|---------------------|---------------------------------------------------------------------------|
| **Performance**     | Handle up to **1,000 reminders/minute** with <200 ms per SOAP call.       |
| **Scalability**     | Scale horizontally: multiple pods behind an AWS ALB in EKS.               |
| **Reliability**     | 99.9 % uptime SLA; automatic fail‑over for scheduler pods.               |
| **Security**        | AES‑256 encryption for DB at rest; TLS 1.2+ for all inbound/outbound.     |
| **Observability**   | Collect logs (JSON) to CloudWatch; metrics scraped by Prometheus.         |
| **Maintainability** | 80 % unit test coverage; modular code (service/repository layers).        |
| **Deployability**   | Fully automated GitHub Actions pipeline: build → test → Docker push → Helm upgrade. |
| **Portability**     | Dockerized; can run locally or in any Kubernetes‑compatible cloud.       |

---