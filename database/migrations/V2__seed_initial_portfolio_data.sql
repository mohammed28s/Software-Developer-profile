INSERT INTO skills (skill_name, category, proficiency_level) VALUES
    ('Angular', 'Frontend', 85),
    ('TypeScript', 'Frontend', 85),
    ('Spring Boot', 'Backend', 85),
    ('Java', 'Backend', 85),
    ('PostgreSQL', 'Database', 80),
    ('Docker', 'Tools', 75),
    ('GitHub Actions', 'Tools', 75);

INSERT INTO projects (
    title,
    description,
    tech_stack,
    github_url,
    demo_url,
    image_url
) VALUES (
    'Developer Portfolio',
    'A dynamic full-stack portfolio for presenting software projects, technical skills, professional experience, and a downloadable resume.',
    'Angular, Spring Boot, PostgreSQL, Docker',
    NULL,
    NULL,
    NULL
);
