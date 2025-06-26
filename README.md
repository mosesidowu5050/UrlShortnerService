# 🔗 URL Shortener API (Spring Boot)

A simple and scalable URL Shortener service built using Java and Spring Boot. Inspired by services like Bitly and TinyURL, this API allows users to shorten long URLs, redirect using the short code, and track analytics.

## 🚀 Features

- ✅ Shorten long URLs
- ✅ Unique short code generation
- ✅ Redirection to original URL
- ✅ URL expiration support (optional)
- ✅ Track usage (Planned)
- ✅ Custom alias support (Planned)

## ⚙️ Tech Stack

- **Java 24**
- **Spring Boot 3**
- **Spring Data MongoDB**
- **Maven**
- **Lombok**
- **JUnit** for testing
- **Postman** for manual testing

## 📁 Project Structure


## 📦 API Endpoints

| Method | Endpoint                | Description                |
|--------|-------------------------|----------------------------|
| POST   | `/api/v1/shorten`       | Create short URL           |
| GET    | `/{shortCode}`          | Redirect to original URL   |
| GET    | `/api/v1/urls/{id}`     | Get original URL details   |

### Example: Shorten URL

**Request**
```json
POST /api/v1/shorten
{
  "originalUrl": "https://example.com/some/very/long/path"
}

{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://example.com/some/very/long/path",
  "createdAt": "2025-06-26T18:45:00",
  "expiresAt": "2025-07-26T18:45:00"
}


# Clone repository
git clone https://github.com/your-username/url-shortener.git
cd url-shortener

# Build and run
./mvnw clean install
./mvnw spring-boot:run


📌 To-Do / Improvements
 Add custom aliases

 Add usage analytics

 Add URL expiration logic

 Add Swagger/OpenAPI documentation

 Dockerize the project

👨‍💻 Author
Idowu Moses Babatunde @mosesidowu5050


---

Let me know if you want me to generate the Postman collection, Swagger setup, or Dockerfile for deployment too.
