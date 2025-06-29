CREATE TABLE IF NOT EXISTS evaluations (
    id SERIAL PRIMARY KEY,
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    chronological_age_months INTEGER NOT NULL,
    children_id INTEGER NOT NULL,
    evaluator_id INTEGER NOT NULL
);
