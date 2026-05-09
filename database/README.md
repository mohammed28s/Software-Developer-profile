# Portfolio Database

This folder contains the local PostgreSQL setup and versioned schema migrations for the developer portfolio.

## Tables

- `admin_users`: private administrator accounts with encrypted password hashes.
- `projects`: public showcase projects.
- `skills`: categorized technical skills.
- `experience`: professional timeline entries.

## Local Database

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Connect with:

```bash
psql postgresql://portfolio_user:portfolio_password@localhost:5432/portfolio_db
```

## Migrations

The files in `database/migrations` use Flyway naming conventions. When the Spring Boot backend is created, these can be copied or referenced from `src/main/resources/db/migration`.
