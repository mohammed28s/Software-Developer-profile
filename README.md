# 🚀 Software Developer Portfolio

<p align="center">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img alt="Angular" src="https://img.shields.io/badge/Angular-SPA-DD0031?style=for-the-badge&logo=angular&logoColor=white">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
  <img alt="Docker" src="https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</p>

<p align="center">
  <strong>A professional, dynamic portfolio platform for showcasing software development expertise to recruiters.</strong>
</p>

---

## ✨ Project Overview

This project is a decoupled full-stack portfolio system designed to present a developer's professional profile, technical skills, projects, work experience, and resume in a clean recruiter-focused experience.

The portfolio is intentionally dynamic: content is stored in a relational database instead of being hard-coded, allowing projects, skills, experience, images, and resume assets to be managed through secure backend APIs.

---

## 🎯 Core Goals

- 🧑‍💻 Showcase software development experience professionally
- 📂 Display a dynamic project portfolio
- 🛠️ Present categorized technical skills
- 🧾 Support resume/CV download as PDF
- 🔐 Provide secure admin APIs for private content management
- 🐳 Run locally with Docker and PostgreSQL
- ☁️ Prepare for cloud deployment on AWS EC2

---

## 🧱 Architecture

```text
Recruiter / Visitor
        │
        ▼
Angular Frontend SPA
        │
        ▼
Spring Boot REST API
        │
        ▼
PostgreSQL Database
```

| Layer | Technology | Purpose |
|---|---|---|
| 🎨 Frontend | Angular | Responsive single-page portfolio experience |
| ⚙️ Backend | Spring Boot | REST API, business logic, validation, security |
| 🗄️ Database | PostgreSQL | Persistent relational content storage |
| 🧬 Migration | Flyway | Versioned database schema changes |
| 🐳 DevOps | Docker Compose | Local containerized development |
| ☁️ Hosting Target | AWS EC2 | Production deployment target |

---

## 📦 Current Repository Structure

```text
.
├── backend/                 # Spring Boot REST API
├── database/                # SQL migrations and database notes
├── docker-compose.yml       # PostgreSQL + backend services
├── profile_srs.txt          # Software Requirements Specification
└── README.md
```

---

## 🗄️ Database Design

The database is built around dynamic portfolio content.

| Table | Purpose |
|---|---|
| `projects` | Professional software projects |
| `skills` | Technical skills grouped by category |
| `experience` | Work experience and achievements |
| `admin_users` | Internal administrator access |

### Main Fields

```text
projects:
id, title, description, tech_stack, github_url, demo_url, image_url, created_at

skills:
id, skill_name, category, proficiency_level

experience:
id, company_name, job_title, start_date, end_date, achievements, current_job

admin_users:
id, username, password_hash
```

---

## 🔌 API Contract

### 🌍 Public API

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/projects` | Fetch showcase projects |
| `GET` | `/api/skills` | Fetch categorized technical skills |
| `GET` | `/api/experience` | Fetch professional timeline |
| `GET` | `/api/assets/cv` | Fetch latest CV metadata |
| `GET` | `/api/assets/profile-image` | Fetch profile image metadata |

### 🔐 Admin API

Admin endpoints are protected with HTTP Basic authentication.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/admin/projects` | Create a project |
| `PUT` | `/api/admin/projects/{id}` | Update a project |
| `DELETE` | `/api/admin/projects/{id}` | Delete a project |
| `POST` | `/api/admin/skills` | Create a skill |
| `PUT` | `/api/admin/skills/{id}` | Update a skill |
| `DELETE` | `/api/admin/skills/{id}` | Delete a skill |
| `POST` | `/api/admin/experience` | Create experience |
| `PUT` | `/api/admin/experience/{id}` | Update experience |
| `DELETE` | `/api/admin/experience/{id}` | Delete experience |
| `POST` | `/api/admin/upload-cv` | Upload latest resume PDF |
| `PUT` | `/api/admin/profile-image` | Upload profile image |

---

## 🚀 Run Locally

### Prerequisites

- Java 17+
- Maven 3.9+
- Docker Desktop

### Start the full stack

```bash
docker compose up -d --build
```

### Backend URL

```text
http://localhost:8080
```

### Test public endpoints

```bash
curl http://localhost:8080/api/projects
curl http://localhost:8080/api/skills
curl http://localhost:8080/api/experience
```

---

## 🧪 Backend Tests

```bash
cd backend
mvn test
```

---

## 🔐 Local Admin Credentials

Default development credentials:

```text
Username: admin
Password: admin123
```

For production, set secure environment variables:

```bash
ADMIN_USERNAME=your_admin_user
ADMIN_PASSWORD=your_secure_password
```

---

## 🛠️ Tech Stack

| Category | Tools |
|---|---|
| Backend | Java, Spring Boot, Spring Security, Spring Data JPA |
| Database | PostgreSQL, Flyway |
| DevOps | Docker, Docker Compose |
| Build | Maven |
| Version Control | Git, GitHub |
| Planned Frontend | Angular |
| Planned CI/CD | GitHub Actions |
| Planned Hosting | AWS EC2 |

---

## 📍 Project Status

| Area | Status |
|---|---|
| SRS | ✅ Completed |
| Database schema | ✅ Completed |
| Spring Boot backend | ✅ Completed |
| Docker local environment | ✅ Completed |
| Angular frontend | 🟡 Planned |
| CI/CD pipeline | 🟡 Planned |
| AWS deployment | 🟡 Planned |

---

## 📄 License

This project is currently maintained as a personal software developer portfolio project.
