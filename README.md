
# E-Commerce Backend REST API

A production-style backend REST API for an online shopping platform, built with 
Spring Boot 3. Handles user authentication, product catalog management, and order 
processing with real-time inventory control — the server-side engine that powers 
apps like Flipkart or Amazon.

## Features

- **JWT Authentication** — stateless login with access token (24h) + refresh token (7 days)
- **Role-Based Access Control** — `ROLE_USER` and `ROLE_ADMIN` with method-level security
- **Product Catalog** — CRUD, search by keyword, filter by category/price, pagination
- **Order Processing** — atomic stock deduction using `@Transactional`, rollback on failure
- **Soft Delete** — products are deactivated, not removed, to preserve historical order integrity
- **Global Exception Handling** — consistent JSON error responses across all endpoints
- **Swagger / OpenAPI Docs** — interactive API documentation with built-in JWT auth support
- **Automated Tests** — unit tests (Mockito) and integration tests (MockMvc + Spring Security)

## Tech Stack

| Component | Technology |
|---|---|
| Core Framework | Spring Boot 3.2.3 |
| Security | Spring Security + JWT |
| Database ORM | Spring Data JPA + Hibernate |
| Database | MySQL 8 (H2 for local dev) |
| API Docs | Swagger / SpringDoc OpenAPI |
| Boilerplate | Lombok |
| Build Tool | Maven |
| Language | Java 17 |

## Architecture

Classic 3-layer architecture:
```
Client → JWT Filter → Controller → Service → Repository → MySQL
```
- **Controller** — handles HTTP requests, delegates to services
- **Service** — business logic (stock validation, order calculations, password hashing)
- **Repository** — Spring Data JPA interfaces for database access
- **DTOs** — separate the API contract from internal entity structure

## Entity Relationships

```
User ──ManyToMany── Role
User ──OneToMany──── Order
Order ──OneToMany(Cascade.ALL)── OrderItem
OrderItem ──ManyToOne── Product
```

## API Endpoints

**Auth**
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login, returns access + refresh token |
| POST | `/api/auth/refresh` | Get a new access token using refresh token |

**Products**
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | List products (paginated, searchable, filterable) |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Add a product (Admin only) |
| PUT | `/api/products/{id}` | Update a product (Admin only) |
| DELETE | `/api/products/{id}` | Soft-delete a product (Admin only) |

**Orders**
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/orders` | Place an order |
| GET | `/api/orders` | View order history |
| GET | `/api/orders/{id}` | Get order details |

All endpoints except `/api/auth/**` require an `Authorization: Bearer <token>` header.

## How JWT Authentication Works
1. User registers → password hashed with BCrypt (random salt + cost factor)
2. User logs in → credentials verified → server issues access token (24h) and refresh token (7 days)
3. Client sends `Authorization: Bearer <token>` on every subsequent request
4. A custom `OncePerRequestFilter` intercepts each request, validates the JWT signature and expiry, and sets authentication in `SecurityContextHolder`
5. `@PreAuthorize("hasRole('ADMIN')")` enforces role-based access on protected endpoints
6. When the access token expires, the client calls `/api/auth/refresh` to get a new pair without re-entering credentials

## Getting Started

**Clone the repo:**
```bash

```

**Configure the database** in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=yourSecretKey
jwt.expiration=86400000
```

**Run it:**
```bash
mvn clean install
mvn spring-boot:run
```
App runs at `http://localhost:8080`

