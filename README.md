# Bajaj Finserv Health Limited (BFHL) API

This project is a REST API built using Java Spring Boot for the BFHL Qualifier Assessment (Chitkara University). It exposes a POST endpoint `/bfhl` that processes a list of mixed alphanumeric values and symbols, performs calculations, and categorizes the data.

## Features

- **Data Classification:** Filters incoming string data into:
  - `odd_numbers` (as strings)
  - `even_numbers` (as strings)
  - `alphabets` (converted to uppercase)
  - `special_characters` (symbols)
- **Math Logic:** Calculates the sum of all numerical values in the array and returns it under the `sum` key.
- **String Manipulation:** Concatenates all alphabetic values in reverse order with alternating capitalization (uppercase on even index, lowercase on odd index).
- **Graceful Error Handling:** Provides robust HTTP 400 bad request returns with descriptive details if the JSON body is malformed.
- **DTO Architecture:** Clean separation of request and response transfer payloads.
- **JUnit 5 & Mockito Tests:** Automated controller integration tests and service unit test cases.

---

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 3.4.x
- **Build Tool:** Maven (mvnw wrapper included)

---

## Getting Started

### Prerequisites
Make sure you have Java 21 installed.

### Build the Project
To compile the classes and build the jar file:
```bash
./mvnw clean package
```

### Run the Application
Start the Spring Boot application locally:
```bash
./mvnw spring-boot:run
```
The server will run on `http://localhost:8080`.

### Run Tests
To run the automated JUnit test suite:
```bash
./mvnw test
```

---

## API Specification

### Endpoint: `/bfhl`
* **Method:** `POST`
* **Content-Type:** `application/json`

#### Request Payload Example
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

#### Response Payload Example
```json
{
  "is_success": true,
  "user_id": "diwanshu_baskota_16082003",
  "email": "diwanshu1618.be23@chitkarauniversity.edu.in",
  "roll_number": "2311981618",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```
