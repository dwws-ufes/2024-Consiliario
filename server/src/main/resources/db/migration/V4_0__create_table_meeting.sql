CREATE TABLE IF NOT EXISTS consiliario.Meeting (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    datetime TIMESTAMP NOT NULL,
    teacher UUID NOT NULL,
    student UUID NOT NULL,
    location VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);