## 1. Project Overview

**Project Name:**  
Kafka‑Driven E‑commerce Pipeline

**Author:**  
Dhia Ouerghemmi

**Date:**  
June 17, 2025

**Version:**  
1.0

---

## 2. Purpose & Business Justification

Modern online retailers struggle with tightly coupled, synchronous integration between order placement, payment processing, inventory management, and shipping. This leads to:

- **Order delays** under peak load  
- **Inventory oversells** when stock is reserved but not confirmed  
- **Manual interventions** that do not scale  

This project implements a **decoupled**, **event‑driven** architecture using Apache Kafka and the Saga pattern to ensure **reliability**, **scalability**, and **resilience** across microservices.

---

## 3. Objectives & Success Metrics

| Objective                                                         | Success Metric                                    |
|-------------------------------------------------------------------|---------------------------------------------------|
| Reduce average order‑to‑shipment time                              | ≤ 200 ms end‑to‑end latency under 100 TPS         |
| Prevent inventory oversells                                        | 0 oversells in 10,000 orders processed            |
| Achieve service availability                                       | ≥ 99.9 % uptime per service/month                 |
| Enable autonomous scaling                                          | Auto‑scale pods when CPU > 70 % for 5 min          |
| Provide full observability                                         | 100 % of critical flows traced via Jaeger         |

---

## 4. Scope Boundaries

### In‑Scope
- Order Service: publish `OrderCreated` events  
- Inventory Service: consume, reserve/reject stock  
- Payment Service: process payments, compensate on failure  
- Shipping Service: schedule shipments upon payment success  
- Notification Service: notify user via email/SMS/webhook  

### Out‑of‑Scope
- Returns & refunds management  
- Customer support workflow  
- Third‑party marketplace integrations  

---


## 5. Assumptions & Constraints

- **Kafka Throughput:** ≥ 10,000 events/sec  
- **Budget Constraint:** Limited to free-tier cloud resources and local hardware  
- **Team Capacity:** Single‑developer project — timelines account for part‑time work  
- **Technology Stack:** Java 17, Spring Boot, PostgreSQL

---

## 8. Risks & Mitigation

| Risk                                   | Likelihood | Impact    | Mitigation                                                       |
|----------------------------------------|------------|-----------|------------------------------------------------------------------|
| Schema incompatibilities during updates| Medium     | High      | Enforce strict Avro schema registry policies                     |
| Message loss under network partition   | Low        | High      | Enable Kafka replication factor ≥ 3; use acknowledgments         |
| Payment gateway downtime               | Medium     | High      | Implement retry logic with exponential backoff                   |
| Kubernetes misconfiguration            | Low        | Medium    | Use Terraform modules and helm charts with peer reviews          |
| Single‑developer knowledge gap         | High       | Medium    | Document architecture in `docs/`; seek community forum support   |

---
