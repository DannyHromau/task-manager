DROP TYPE IF EXISTS status_enum;
DROP TYPE IF EXISTS priority_enum;
CREATE TYPE status_enum AS ENUM ('awaiting', 'in_progress', 'completed');
CREATE TYPE priority_enum AS ENUM ('high', 'medium', 'low');