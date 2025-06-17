# Requirements Traceability Matrix

This matrix maps each requirement defined in the SRS to its corresponding design artifacts and test cases, ensuring full coverage from specification through implementation and validation.

---

## 1. Functional Requirements

| Req ID                   | Requirement Description                                                                                        | SRS Section | Design Artifact                                       | Test Case ID |
| ------------------------ | -------------------------------------------------------------------------------------------------------------- | ----------- | ----------------------------------------------------- | ------------ |
| **Order Service**        |
| REQ-ORD-001              | Publish `OrderCreated` event to Kafka when a new order is submitted.                                           | 3.1.1       | `docs/02_architecture/sequence-overview.md`           | TC-ORD-001   |
| REQ-ORD-002              | Persist new order in `orders` table with status `PENDING`.                                                     | 3.1.1       | `docs/03_data_modeling/erd.md`                        | TC-ORD-002   |
| **Inventory Service**    |
| REQ-INV-001              | Consume `OrderCreated` events from Kafka.                                                                      | 3.1.2       | `docs/02_architecture/component-diagram.md`           | TC-INV-001   |
| REQ-INV-002              | If sufficient stock, decrement inventory and publish `InventoryReserved`; otherwise publish `InventoryFailed`. | 3.1.2       | `docs/04_low_level_design/class-diagrams.md`          | TC-INV-002   |
| REQ-INV-003              | Expose REST endpoint `GET /inventory/{productId}` to report current stock.                                     | 3.1.2       | `docs/04_low_level_design/api-specification.md`       | TC-INV-003   |
| **Payment Service**      |
| REQ-PAY-001              | Consume `InventoryReserved` events.                                                                            | 3.1.3       | `docs/02_architecture/sequence-overview.md`           | TC-PAY-001   |
| REQ-PAY-002              | Invoke external payment gateway; on success publish `PaymentSucceeded`; on failure publish `PaymentFailed`.    | 3.1.3       | `docs/04_low_level_design/interface-definitions.md`   | TC-PAY-002   |
| REQ-PAY-003              | Retry failed payments up to 3 times with exponential backoff.                                                  | 3.1.3       | `docs/05_scalability_performance/scaling-strategy.md` | TC-PAY-003   |
| **Shipping Service**     |
| REQ-SHP-001              | Consume `PaymentSucceeded` events and schedule carrier via REST API.                                           | 3.1.4       | `docs/02_architecture/sequence-overview.md`           | TC-SHP-001   |
| REQ-SHP-002              | Publish `OrderShipped` event upon successful scheduling.                                                       | 3.1.4       | `docs/04_low_level_design/class-diagrams.md`          | TC-SHP-002   |
| REQ-SHP-003              | On carrier scheduling failure, publish `ShippingFailed`.                                                       | 3.1.4       | `docs/04_low_level_design/interface-definitions.md`   | TC-SHP-003   |
| **Notification Service** |
| REQ-NOT-001              | Consume all final-state events (`InventoryFailed`, `PaymentFailed`, `OrderShipped`).                           | 3.1.5       | `docs/02_architecture/component-diagram.md`           | TC-NOT-001   |
| REQ-NOT-002              | Send email/SMS/webhook notifications to user with the updated order status.                                    | 3.1.5       | `docs/04_low_level_design/api-specification.md`       | TC-NOT-002   |

---

## 2. Non‑Functional Requirements

| Req ID        | Requirement Description                                                                                     | SRS Section         | Design Artifact                                        | Test Case ID |
| ------------- | ----------------------------------------------------------------------------------------------------------- | ------------------- | ------------------------------------------------------ | ------------ |
| NFR-PERF-001  | End-to-end processing latency < 200 ms for 95% of orders under 100 TPS.                                     | 3.2 Performance     | `docs/05_scalability_performance/capacity-planning.md` | TC-NFR-001   |
| NFR-SCAL-001  | Services auto-scale in Kubernetes when CPU > 70% for 5 min.                                                 | 3.2 Scalability     | `docs/05_scalability_performance/scaling-strategy.md`  | TC-NFR-002   |
| NFR-REL-001   | Maintain 99.9% uptime per service per calendar month.                                                       | 3.2 Reliability     | `docs/08_observability/monitoring-architecture.md`     | TC-NFR-003   |
| NFR-SEC-001   | Encrypt all communications (mTLS for Kafka, HTTPS for REST); enforce OAuth 2.0 for service-to-service auth. | 3.2 Security        | `docs/09_security_compliance/security-architecture.md` | TC-NFR-004   |
| NFR-MAINT-001 | Code coverage ≥ 80% for unit tests; adhere to Clean Code & SOLID principles.                                | 3.2 Maintainability | `docs/10_testing_qa/test-plan.md`                      | TC-NFR-005   |
| NFR-OBS-001   | Expose Prometheus metrics; distributed tracing via Jaeger; logs in JSON for ELK ingestion.                  | 3.2 Observability   | `docs/08_observability/trace-diagram.md`               | TC-NFR-006   |
| NFR-PORT-001  | Docker images run on any CNCF-compliant Kubernetes cluster.                                                 | 3.2 Portability     | `docs/06_infrastructure_iac/network-topology.md`       | TC-NFR-007   |

---
