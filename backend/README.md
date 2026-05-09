# Portfolio Backend

Spring Boot REST API for the dynamic software developer portfolio.

## Public Endpoints

- `GET /api/projects`
- `GET /api/skills`
- `GET /api/experience`
- `GET /api/assets/cv`
- `GET /api/assets/profile-image`

## Admin Endpoints

Admin endpoints use HTTP Basic auth.

Default local credentials:

- username: `admin`
- password: `admin123`

Configure them with `ADMIN_USERNAME` and `ADMIN_PASSWORD` before deployment.

Endpoints:

- `POST /api/admin/projects`
- `PUT /api/admin/projects/{id}`
- `DELETE /api/admin/projects/{id}`
- `POST /api/admin/skills`
- `PUT /api/admin/skills/{id}`
- `DELETE /api/admin/skills/{id}`
- `POST /api/admin/experience`
- `PUT /api/admin/experience/{id}`
- `DELETE /api/admin/experience/{id}`
- `POST /api/admin/upload-cv`
- `PUT /api/admin/profile-image`

## Run Locally

Start PostgreSQL first:

```bash
docker compose up -d postgres
```

Then run the backend:

```bash
cd backend
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.
