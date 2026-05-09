-- 1. Table for Professional Projects
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    tech_stack VARCHAR(500),
    github_url VARCHAR(255),
    demo_url VARCHAR(255),
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Table for Technical Skills
CREATE TABLE skills (
    id BIGSERIAL PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL,
    category VARCHAR(100),
    proficiency_level INT
);

-- 3. Table for Work Experience
CREATE TABLE experience (
    id BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    achievements TEXT,
    current_job BOOLEAN DEFAULT FALSE
);

-- 4. Table for Admin/User (Internal Access Only)
CREATE TABLE admin_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);
