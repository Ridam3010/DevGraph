# 🚀 DevGraph — Developer Intelligence Platform

A microservices-based developer platform that analyzes coding activity, executes code securely, and builds a dynamic skill graph. Users can solve problems, track results, and receive personalized recommendations.

---

## 📁 Project Structure

```text
DevGraph-Platform/
├── client/         # React 18 Frontend (Vite)
└── server/         # Spring Boot Microservices (Parent POM)
```

---

## 💻 Frontend Overview (`client/`)

Status: `MVP screens pending` ⏳

**Tech Stack:**
- **UI Framework:** React 18 (Vite)
- **Styling:** Tailwind CSS + Radix UI
- **State/Routing:** React Router v6 + Context API
- **Code Editor:** Monaco Editor
- **Real-time:** WebSockets (SockJS + STOMP)

**Running the frontend:**
```bash
cd client
npm install
npm run dev # Starts on http://localhost:5173
```

---

## ⚙️ Backend Overview (`server/`)

Status: `Phase 1 (Setup & Architecture) in progress` ⏳ | `Core Work Plan in Progress`

**Core Tech Stack**

| Layer | Technology | Status |
| :--- | :--- | :--- |
| **Backend** | Spring Boot 3.x + Java 17 | ⏳ Pending |
| **Database** | MySQL 8.0 (Spring Data JPA) | ⏳ Pending |
| **Cache** | Redis (TTL & fast access) | ⏳ Pending |
| **Auth** | JWT (Access + Refresh Tokens) + Spring Security | ⏳ Pending |
| **Integrations** | Apache Kafka (Async messaging) | ⏳ Pending |
| **Infrastructure** | Docker Compose + Spring Cloud Gateway | ⏳ Pending |

**Infrastructure (Docker)**
Spins up MySQL, Redis, and Kafka/Zookeeper.

```bash
cd server
docker-compose up -d
```

**Running the backend:**
```bash
cd server
./mvnw clean install
./mvnw spring-boot:run -pl api-gateway # Example for running a specific service
```

---

## ⚠️ Key Developer Rules

1. **Database:** Do not use raw SQL. Use Spring Data JPA `@Entity` and `Repository` interfaces.
2. **Security:** All endpoints (except login/register) must be secured behind JWT verification in the Gateway.
3. **Async Logic:** Any heavy processing (Code Execution, Analytics) MUST be pushed to Kafka and handled asynchronously.
4. **Sandboxing:** User code MUST only be executed inside isolated Docker containers, never on the host machine.
5. **Secrets:** Never commit secrets (DB passwords, JWT secrets) to version control. Use `.env` files.

---

## 🧠 Mentorship & Learning Objectives

Because this project is a **Learning Journey**, we will prioritize understanding *why* we do things over just writing code. 
- **Concept First:** Before writing any complex logic (e.g., Kafka consumers, JWT filters), the Senior Architect (Mentor) will explain the theory and architecture.
- **Code Reviews:** Every PR will be reviewed with detailed feedback on best practices, REST design, and Clean Code principles.
- **Real-World Practices:** You will learn enterprise patterns like DTO mapping, global exception handling, and database migrations.

---

## DevGraph Backend — Developer Work Plan

### BE1 — Infrastructure Developer (Senior Architect / Mentor)
*Provides architecture, boilerplate, and explanations for BE2.*

#### Week 1
**Day 1**
- [ ] Create the GitHub repository and branch protection rules
- [ ] Initialize Spring Boot multi-module project (Parent POM)
- [ ] Create `docker-compose.yml` with MySQL 8.0 and Redis containers

**Day 2**
- [ ] Create `api-gateway` module with Spring Cloud Gateway
- [ ] Create `auth-service` module
- [ ] Set up Spring Security and JWT utility classes (generate/validate tokens)

**Day 3 & 4**
- [ ] Add Kafka and Zookeeper/Kraft to `docker-compose.yml`
- [ ] Create `shared-library` module for common DTOs and exception handling

**Day 5**
- [ ] Set up Dockerfile templates for Java microservices
- [ ] Configure centralized logging (Logback/SLF4J) to output JSON logs

#### Week 2
**Day 6 & 7**
- [ ] Create `code-execution-service` module and Kafka consumers
- [ ] Build isolated Docker sandbox logic using Java `ProcessBuilder`

---

### BE2 — Business Logic Developer (Backend Engineer / You)

#### Week 1
**Day 1 — Most Important Task**
> 📚 **Learning Focus:** ORM (Object-Relational Mapping), Spring Data JPA, and Database Schema Design.
- [ ] Write `User`, `Role`, and `Profile` JPA Entities in `user-service`
- [ ] Create Liquibase/Flyway migration scripts for user tables
- [ ] Connect `user-service` to MySQL via Spring Data JPA

**Day 2**
> 📚 **Learning Focus:** RESTful API Design, Controller-Service-Repository Pattern, HTTP Status Codes.
- [ ] Create REST endpoints for `/api/v1/users/register` and `/api/v1/users/login` (interacting with Auth Service)
- [ ] Create `/api/v1/users/{id}/profile` GET/PUT endpoints
- [ ] Test user registration and login flow using Postman

**Day 3**
> 📚 **Learning Focus:** Relational Data Modeling (One-to-Many, Many-to-Many) and Seeding Data.
- [ ] Write `Problem` and `TestCase` JPA Entities in `problem-service`
- [ ] Create repository interfaces for Problem database access
- [ ] Seed database with 5 sample coding problems (Easy, Medium, Hard)

**Day 4**
> 📚 **Learning Focus:** Pagination, Filtering, and Distributed Caching with Redis.
- [ ] Create REST endpoints: `GET /api/v1/problems`, `GET /api/v1/problems/{id}`
- [ ] Implement pagination and filtering for problem lists
- [ ] Integrate Redis caching for `GET /api/v1/problems/{id}`

**Day 5**
> 📚 **Learning Focus:** Event-Driven Architecture and decoupling services.
- [ ] Write `Submission` JPA Entity in `submission-service`
- [ ] Create endpoint `POST /api/v1/submissions`
- [ ] Ensure `POST` endpoint saves submission as `PENDING` and pushes Kafka event

#### Week 2
**Day 6**
> 📚 **Learning Focus:** Consuming Events, Async Processing, and Aggregating Data.
- [ ] Create `analytics-service` module and database schema
- [ ] Consume execution results from Kafka in Analytics Service
- [ ] Calculate user success rate and average execution time

**Day 7**
> 📚 **Learning Focus:** Graph Data Structures in practice and complex JSON serialization.
- [ ] Create `skill-graph-service` module
- [ ] Define Graph nodes (Skills: Arrays, Trees, DP) and edges (User proficiency)
- [ ] Write API to fetch a user's skill graph JSON for the frontend

---

### FE1 — Frontend & Integrations Developer (You)

#### Week 1
**Day 1 & 2**
> 📚 **Learning Focus:** React 18, State Management, and secure JWT handling on the client.
- [ ] Initialize React + Vite project (`client`)
- [ ] Create Login and Registration pages
- [ ] Implement JWT storage in HTTP-only cookies or LocalStorage
- [ ] Create Axios interceptor to attach JWT to outgoing requests

**Day 3 & 4**
> 📚 **Learning Focus:** Integrating 3rd party libraries (Monaco Editor) and API consumption.
- [ ] Build "Problem Explorer" and "Problem Details" pages
- [ ] Integrate Monaco Editor (VS Code web editor)
- [ ] Wire up "Run Code" button to hit Submission Service

**Day 5**
> 📚 **Learning Focus:** Real-time WebSockets and asynchronous UI updates.
- [ ] Add WebSocket or Polling hook to listen for submission results
- [ ] Build result modal (Success, Wrong Answer, Time Limit Exceeded)

---

## 🛡️ Week 1 Exit Checklist — All Must Pass Before Week 2

- [ ] `docker-compose up` spins up MySQL, Redis, and Kafka without errors
- [ ] API Gateway successfully routes traffic to Auth and User services
- [ ] JWT tokens are successfully generated on login and validated on protected routes
- [ ] Redis successfully caches problem descriptions
- [ ] User can register, login, and view a list of problems via Postman
- [ ] Frontend React app can display the problem list using mock data or API
- [ ] All database schemas created and migrations run successfully

## 🛡️ Week 2 Exit Checklist — All Must Pass Before Week 3

- [ ] Submissions successfully publish to Kafka `submissions` topic
- [ ] Code Execution Service successfully consumes and runs Python/Java code in a sandbox
- [ ] Execution results are published back to `results` topic
- [ ] Analytics Service updates user stats in database based on results
- [ ] Frontend Monaco editor can submit code and display the async result
- [ ] Skill Graph API returns a valid structure based on user's solved problems
- [ ] Postman collection updated with all new endpoints and shared
