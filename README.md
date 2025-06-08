```markdown
# Personal Health Record Manager

**Project Status:** In Progress

---

## Real-World Problem

Many patients today lack a centralized, secure, and user-friendly system to store, view, and share their basic health metrics. Important data—such as blood pressure readings, weight history, and allergy information—is often scattered across paper records, disparate clinic portals, or mobile apps that don’t interoperate. This fragmentation leads to:

- **Incomplete medical histories** during emergencies
- **Repeated data entry** when visiting multiple providers
- **Reduced patient empowerment** over their own data
- **Difficulty tracking long-term trends** in health metrics

---

## Existing Solutions

| Solution              | Data Source Support      | Centralization | Cost         | Data Export | Interoperability |
| --------------------- | ------------------------ | -------------- | ------------ | ----------- | ---------------- |
| Clinic Portals        | Clinic’s own EMR only    | No             | Free         | Limited     | Proprietary      |
| Mobile Health Apps    | Wearables & manual entry | Partially      | Freemium     | CSV/None    | API varies       |
| Personal EHR Services | Clinics + Patients       | Yes            | Subscription | CSV/PDF     | FHIR/HL7         |

---

## Comparison of Existing Solutions

| Feature                   | Clinic Portals | Mobile Apps     | Personal EHR Services |
| ------------------------- | -------------- | --------------- | --------------------- |
| **Complete History**      | No             | Depends on user | Mostly yes            |
| **Cross-Provider Access** | No             | No              | Yes                   |
| **User Control**          | Limited        | Yes             | Yes                   |
| **Cost**                  | Typically free | Freemium        | Subscription          |
| **Standards Compliance**  | No             | Rarely          | Often (FHIR-ready)    |

---

## Proposed Solution

We propose a **Personal Health Record Manager**:  
A lightweight, microservices-based platform that provides each patient with their own secure REST API for CRUD management of health metrics. Key principles:

- ✅ **True Data Ownership:** Users register, authenticate, and own their records
- ✅ **Centralized Storage:** All metrics in one place, accessible via API
- ✅ **Standards-Friendly:** JSON-based REST with potential FHIR compatibility
- ✅ **Extensible Microservices:** Easy to add new metrics or features

---

## Solution’s Features & Components

1. **Auth Service**
   - User sign-up, login (JWT), password hashing (BCrypt)
   - Role-based access preparation
2. **Record Service**
   - CRUD endpoints for HealthRecord (blood pressure, weight, allergies)
   - Per-user isolation and paging/filtering
3. **API Gateway**
   - Central routing, CORS, rate limiting
   - Single entry point for all services
4. **Config Service**
   - Centralized configuration (Spring Cloud Config, Git/S3 backend)
5. **Infrastructure as Code**
   - Terraform modules for AWS VPC, EKS, RDS & ECR
   - Kubernetes manifests for services and ingress
6. **Monitoring & Observability**
   - Micrometer + Prometheus exporters in each service
   - Prometheus + Grafana deployed via Helm
   - Prebuilt dashboards for latency, error rates, resource usage
7. **CI/CD**
   - GitHub Actions for build, test, Docker build & push, EKS deployment
   - Terraform plan/apply pipeline with branch protection

---

## Challenges :

1.
2.
3.
4.
5.

---
```
