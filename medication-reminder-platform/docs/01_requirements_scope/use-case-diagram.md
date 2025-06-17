# Use Case Diagrams

This document describes the primary actors and use cases for the Medication Reminder Service. It includes visual diagrams (Mermaid) and textual descriptions.

## Table of Contents

1. [Actors](#actors)  
2. [Use Case Diagram](#use-case-diagram)  
3. [Detailed Use Case Descriptions](#detailed-use-case-descriptions)  


## Actors

- **Patient**  
  Receives reminders and can view upcoming doses.  
- **Administrator**  
  Manages patient records and medication plans.  
- **Scheduler**  
  (System component) polls the database and triggers notifications.  
- **Notification Service**  
  (System component) sends emails/SMS via AWS SES/SNS.  


## Use Case Diagram
![Use Case Diagram](/docs/01_requirements_scope/use-case-diagram.png)
