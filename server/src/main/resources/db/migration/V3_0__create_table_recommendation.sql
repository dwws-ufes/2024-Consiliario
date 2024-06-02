CREATE TABLE IF NOT EXISTS consiliario.Recommendation (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    sender UUID NOT NULL,
    receiver UUID NOT NULL,
    url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);