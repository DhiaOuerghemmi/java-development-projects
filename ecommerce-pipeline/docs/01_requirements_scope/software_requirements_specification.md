## 1. Introduction

### 1.1 Purpose  
This SRS defines the functional and non‑functional requirements for the Kafka‑Driven E‑commerce Pipeline. It serves as the foundation for design, implementation, and testing.

### 1.2 Scope  
The system will orchestrate order placement, inventory reservation, payment processing, shipping scheduling, and user notifications using Apache Kafka and microservices built with Spring Boot.

### 1.3 Definitions, Acronyms, Abbreviations  
| Term                  | Definition                                      |
|-----------------------|-------------------------------------------------|
| **Kafka**             | Distributed event streaming platform            |
| **Avro**              | Apache data serialization system                |
| **Saga**              | Distributed transaction pattern                 |
| **TPS**               | Transactions per second                         |
| **SLA**               | Service Level Agreement                         |

### 1.4 References  
- IEEE 29148:2018 Software & Systems Engineering — SRS  
- Apache Kafka Documentation  
- Spring Cloud Stream Reference Guide  

---

## 2. Overall Description

### 2.1 System Context  
![System Context Diagram](../02_architecture/context-diagram.md)

### 2.2 User‑Stories Summary  
1. **As a customer**, I want to place an order so that I can purchase products.  
2. **As a system**, I want to reserve inventory to prevent oversells.  
3. **As a system**, I want to process payments reliably.  
4. **As a system**, I want to schedule shipping once payment succeeds.  
5. **As a customer**, I want to receive notifications about my order status.

### 2.3 Assumptions & Dependencies  
- PostgreSQL database accessible for inventory and order state.  
- External payment gateway with REST API.  
- Docker Registry for container images.  
- Kubernetes cluster configured with Helm.

---

## 3. Specific Requirements

### 3.1 Functional Requirements

#### 3.1.1 Order Service  
- **REQ‑ORD‑001**: Publish `OrderCreated` to Kafka when a new order is submitted.  
- **REQ‑ORD‑002**: Store incoming orders in `orders` table with status `PENDING`.  

#### 3.1.2 Inventory Service  
- **REQ‑INV‑001**: Consume `OrderCreated` events.  
- **REQ‑INV‑002**: If sufficient stock, decrement inventory and publish `InventoryReserved`; otherwise publish `InventoryFailed`.  
- **REQ‑INV‑003**: Expose REST endpoint `GET /inventory/{productId}` for current stock.

#### 3.1.3 Payment Service  
- **REQ‑PAY‑001**: Consume `InventoryReserved` events.  
- **REQ‑PAY‑002**: Call external payment gateway REST API; on success publish `PaymentSucceeded`; on failure publish `PaymentFailed`.  
- **REQ‑PAY‑003**: Retry payment up to 3 times with exponential backoff.

#### 3.1.4 Shipping Service  
- **REQ‑SHP‑001**: Consume `PaymentSucceeded` events.  
- **REQ‑SHP‑002**: Schedule carrier via REST API and publish `OrderShipped`.  
- **REQ‑SHP‑003**: On notification of carrier failure, publish `ShippingFailed`.

#### 3.1.5 Notification Service  
- **REQ‑NOT‑001**: Consume all final state events (`InventoryFailed`, `PaymentFailed`, `OrderShipped`).  
- **REQ‑NOT‑002**: Send email/SMS/webhook to user with order status update.

---

### 3.2 Non‑Functional Requirements

| Category        | Requirement                                                                                         |
|-----------------|-----------------------------------------------------------------------------------------------------|
| **Performance** | End‑to‑end processing latency < 200 ms for 95 % of orders under 100 TPS.                             |
| **Scalability** | Each microservice shall scale horizontally in K8s when CPU > 70 % for continuous 5 minutes.         |
| **Reliability** | Services must maintain 99.9 % uptime per calendar month.                                             |
| **Security**    | All communications encrypted (mTLS for Kafka, HTTPS for REST). Roles enforced via OAuth 2.0.         |
| **Maintainability** | Code coverage ≥ 80 % for unit tests. Adhere to Clean Code and SOLID principles.                |
| **Observability** | Expose Prometheus metrics; tracing via Jaeger; logs in JSON format ingested by ELK.              |
| **Portability** | Docker images must run on any CNCF‑compliant Kubernetes cluster.                                     |

---

## 4. Appendices

- **A. Glossary**: Detailed definitions of domain terms.  
- **B. External API Specs**: Payment Gateway Swagger document.  
- **C. Data Schemas**: Avro definitions for each Kafka topic.  

---