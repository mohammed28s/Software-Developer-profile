# 📘 Software Requirements Specification (SRS)

<p>
  <img alt="Status" src="https://img.shields.io/badge/Status-SRS%20Defined-2E86C1?style=for-the-badge">
  <img alt="Architecture" src="https://img.shields.io/badge/Architecture-Decoupled%20Full--Stack-8E44AD?style=for-the-badge">
  <img alt="Purpose" src="https://img.shields.io/badge/Purpose-Developer%20Portfolio-27AE60?style=for-the-badge">
</p>

---

## 1. 🧾 Software Requirements Specification (SRS)

> 🟦 **Goal**

The goal is to build a professional, dynamic portfolio to showcase software development expertise to recruiters.

### ✅ Functional Requirements

| Requirement | Description |
|---|---|
| 🌍 Public Access | Visitors can view a summary, professional description, dynamic list of skills, and project portfolio. |
| 📄 Resume Management | Recruiters can download the latest CV as a PDF. |
| 🔄 Dynamic Content | The user can add, update, or delete projects and skills through a private interface. |
| 🔐 Secure Administration | An internal, non-exposed API allows the owner to upload images and the PDF resume. |

---

## 2. 🏗️ System Architecture

> 🟪 **Architecture**

We are utilizing a Decoupled Full-Stack Architecture to ensure the frontend and backend are independent and scalable.

| Layer | Description |
|---|---|
| 🎨 Frontend | Built with Angular, providing a responsive Single Page Application (SPA) experience. |
| ⚙️ Backend | Built with Spring Boot, acting as a REST API to handle business logic and security. |
| 🗄️ Database | PostgreSQL or MySQL for persistent, relational data storage. |
| 🐳 DevOps | Docker for containerization and AWS EC2 for cloud hosting. |

---

## 3. 🗃️ Database Design (ERD)

> 🟩 **Dynamic Content Storage**

To ensure the site is "never fixed," all content is stored in a relational database rather than hard-coded.

| Table | Key Fields |
|---|---|
| 📁 Project | id, title, description, tech_stack, github_url, demo_url, image_url |
| 🛠️ Skill | id, skill_name, category (e.g., Frontend, Backend, Tools) |
| 💼 Experience | id, company_name, job_title, start_date, end_date, achievements |
| 👤 User/Admin | id, username, password (encrypted) |

---

## 4. 🔌 Data Flow & API Contract

> 🟨 **REST Communication**

The communication between layers happens through structured REST endpoints.

### 🌍 Public API (Read-Only)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/projects` | Fetches the list of all showcase projects. |
| GET | `/api/skills` | Fetches categorized technical skills. |
| GET | `/api/experience` | Fetches the professional timeline. |

### 🔐 Admin API (Secure/Write)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/admin/projects` | Creates a new project entry. |
| POST | `/api/admin/upload-cv` | Updates the PDF resume file. |
| PUT | `/api/admin/profile-image` | Updates the main profile photo. |

---

## 5. 🧰 Professional Toolset

| Area | Tool |
|---|---|
| 🔁 Version Control | Git & GitHub. |
| 🏗️ Build Tool | Maven (Java) and Angular CLI. |
| 🧬 Database Migration | Flyway or Liquibase for versioned schema changes. |
| 🚀 CI/CD | GitHub Actions for automated build, test, and deployment to AWS. |
