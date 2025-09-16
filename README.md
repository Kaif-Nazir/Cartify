# 🛒 Cartify

> A Spring Boot based shopping cart application with secure APIs for managing carts, items, and orders.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🖼 Screenshots

Here is how Cartify looks in action:

![Cartify Screenshot](./Screenshot%202025-09-16%20192317.png)

---

## 📌 About the Project  
- Cartify is a **RESTful shopping cart service** built using Spring Boot and JPA.  
- It provides APIs to manage **products, carts, and orders**.

---

## 🚀 Tech Stack  
- **Backend:** Spring Boot, Spring Data JPA, Hibernate
- **Database:** MySQL (or H2 for testing)  
- **Build Tool:** Maven  
- **Language:** Java 21+  

---

## 📦 Helpful Frameworks & Libraries  

- **Lombok** → Reduces boilerplate code using annotations like `@Getter`, `@Setter`, `@AllArgsConstructor`, etc.
- **ModelMapper** → Simplifies conversion between Entities and DTOs.  
- **Hibernate** → ORM for database interaction.

---
  
## ⚡ Features  
✅ Create & manage shopping carts of unique users <br>
✅ Add / update / remove items from cart <br>
✅ Place an order with total amount calculation <br>
✅ Exception handling & validations with `GlobalExceptionHandler` <br>
✅ Secure endpoints with authentication & authorization  

---

## 📂 Project Structure  
- │── controller/ → REST Controllers
- │── model/ → Entities (Category , Cart, CartItem, Product, Order , OrderItems , User)
- │── repository/ → JPA Repositories
- │── service/ → Business Logic Layer
- │── request/ → Request Objects
- │── dto/ → Data Transfer Objects
- │── security/ → ShopConfig

## ⚡ Setup Instructions
### 1. Clone the repository
- Clone the repository to your local machine:
```bash
git clone https://github.com/Kaif-Nazir/Cartify.git
cd Cartify
```
### 2. Setup Database
- Create a database named `shoppingcart` in MySQL.
- Update `application.properties` with your username and password.
```bash
mvn clean install
mvn spring-boot:run

