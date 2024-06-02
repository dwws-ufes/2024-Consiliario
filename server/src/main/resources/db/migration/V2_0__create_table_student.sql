CREATE TABLE IF NOT EXISTS consiliario.Student (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);