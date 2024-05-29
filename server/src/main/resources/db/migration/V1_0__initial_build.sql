SET client_encoding = 'UTF8';

CREATE EXTENSION pgcrypto;

CREATE SCHEMA IF NOT EXISTS consiliario;

CREATE TABLE IF NOT EXISTS consiliario.Users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);