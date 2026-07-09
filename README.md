# ecommerce-backend
E-Commerce REST API with Spring Boot, JWT, MySQL

# 🛒 E-Commerce Backend REST API

A robust and scalable **Java Spring Boot REST API** for an online shopping platform. This project provides all the backend services required for an e-commerce application such as **user authentication, product management, order processing, inventory management, and secure JWT-based authorization**.

Think of it as the server-side engine that powers platforms like **Amazon, Flipkart, or Myntra** by handling business logic, database operations, security, and API communication.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User Registration
- User Login
- JWT Token Authentication
- Password Encryption using BCrypt
- Role-Based Access Control (Admin/User)
- Secure REST APIs

### 👤 User Management
- Register new users
- Login with JWT
- View user profile
- Update user details
- Manage user roles

### 📦 Product Management
- Add Products (Admin)
- Update Product Details
- Delete Products
- View Product Catalog
- Search Products
- Category-wise Products
- Price and Stock Management

### 🛍️ Order Management
- Place Orders
- View Order History
- Order Status Tracking
- Automatic Stock Reduction
- Order Validation

### 📊 Inventory Management
- Real-time Stock Updates
- Prevent Out-of-Stock Orders
- Inventory Validation

### 🗄️ Database Management
- MySQL Database Integration
- Spring Data JPA
- Hibernate ORM
- Entity Relationships

---

# 🛠️ Tech Stack

| Technology | Description |
|------------|-------------|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Secure Token Authentication |
| Spring Data JPA | Database ORM |
| Hibernate | ORM Framework |
| MySQL | Relational Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Code Reduction |
| Postman | API Testing |

---

# 📂 Project Structure

```
src
│
├── controller
│     ├── AuthController
│     ├── ProductController
│     ├── OrderController
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── config
│
├── security
│
├── exception
│
└── EcommerceApplication.java
```

---

# 🔑 Authentication Flow

1. User registers an account.
2. Password is encrypted using BCrypt.
3. User logs in with email and password.
4. Server validates credentials.
5. JWT token is generated.
6. Client sends JWT in Authorization Header.
7. Protected APIs verify token before processing requests.

---

# 📡 REST API Endpoints

## Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/auth/register` | Register User |
| POST | `/api/auth/login` | Login User |

---

## Products

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/products` | Get All Products |
| GET | `/api/products/{id}` | Get Product By ID |
| POST | `/api/products` | Add Product |
| PUT | `/api/products/{id}` | Update Product |
| DELETE | `/api/products/{id}` | Delete Product |

---

## Orders

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/orders` | Place Order |
| GET | `/api/orders` | View Orders |
| GET | `/api/orders/{id}` | Order Details |

---

# 💾 Database Schema

Main Entities:

- User
- Role
- Product
- Category
- Order
- OrderItem

Relationships:

```
User
   │
   ├── Orders
   │
Order
   │
   ├── OrderItems
   │
Product
   │
Category
```

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/yourusername/ecommerce-backend.git
```

---

## Navigate to Project

```bash
cd ecommerce-backend
```

---

## Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=yourSecretKey
jwt.expiration=86400000
```

---

## Install Dependencies

```bash
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

# 🧪 Testing APIs

Use **Postman** or **Swagger UI** to test the endpoints.

Example Login Request:

```http
POST /api/auth/login
```

```json
{
    "email":"user@example.com",
    "password":"password123"
}
```

Response

```json
{
    "token":"eyJhbGciOiJIUzI1NiJ9..."
}
```

---

# 🔒 Security Features

- JWT Authentication
- BCrypt Password Hashing
- Spring Security
- Stateless Sessions
- Role-Based Authorization
- Request Validation
- Exception Handling

---

# 📈 Future Improvements

- Shopping Cart Module
- Wishlist
- Product Reviews & Ratings
- Payment Gateway Integration (Stripe/Razorpay)
- Email Notifications
- Order Tracking
- Coupon & Discount System
- Admin Dashboard
- Docker Deployment
- Redis Caching
- Microservices Architecture

---

# 🤝 Contributing

Contributions are welcome!

1. Fork the repository.
2. Create a new branch.
3. Commit your changes.
4. Push your branch.
5. Open a Pull Request.

---

# 📜 License

This project is licensed under the MIT License.

---

# 👨‍💻 Author

**Your Name**

- GitHub: https://github.com/yourusername
- LinkedIn: https://linkedin.com/in/yourprofile

---

## ⭐ If you found this project helpful, consider giving it a Star!
