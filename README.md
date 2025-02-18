# Open Banking API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-lightblue)
![Liquibase](https://img.shields.io/badge/DB%20Migration-Liquibase-yellow)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-informational)

## **Objective**
Your task is to build a **simple OpenBanking API** that allows users to:
1. Retrieve their account balance
2. Fetch recent transactions
3. Perform a payment initiation

The system should interact with a **mocked external banking service** (e.g., a third-party PSD2 API) to fetch account and transaction details.

---

## **Requirements**

### **1. Stack & Technologies**
- **Java 17+**
- **Spring Boot 3+**
- **Maven or Gradle**
- **H2 Database (or PostgreSQL if preferred)**
- **JPA/Hibernate**
- **RestTemplate or WebClient (to call external services)**
- **JUnit & Mockito (for testing)**

---

### **2. Tasks & Deliverables**

#### **Task 1: Implement Account & Transactions API**
Implement a **REST API** with the following endpoints:

1. **GET /api/accounts/{accountId}/balance**
    - Returns the current account balance. accountId is IBAN

2. **GET /api/accounts/{accountId}/transactions**
    - Returns the last 10 transactions for the given account. accountId is IBAN

3. **POST /api/payments/initiate**
    - Initiates a new payment to an external bank API. The request should be simple IBAN-to-IBAN payment with amount and currency

#### **Task 2: Connect to a Mock External Banking API**
- Simulate an external PSD2/OpenBanking service using a **Mock API** (can be a JSON file, WireMock, or a simple Spring Boot controller).
- Use **RestTemplate/WebClient** to fetch account balance & transaction history from this service.

#### **Task 3: Implement Payment Handling**
- Ensure payment status and its handling is consistent and robust
- Ensure there is enough money on balance
- Payments should be stored in the local database before being sent to the external API.

---

## **Bonus Points**
➕ Implement OAuth2 authentication (client credentials flow)  
➕ Use OpenAPI/Swagger for API documentation  
➕ Integration tests
