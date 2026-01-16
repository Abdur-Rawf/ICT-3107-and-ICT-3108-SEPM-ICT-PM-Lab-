CREATE DATABASE userdb;
USE userdb;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, password, phone) VALUES
('আরিফ আহমেদ', 'arif@example.com', '123456', '০১৭১২৩৪৫৬৭৮'),
('সাবেরা খাতুন', 'sabera@example.com', '123456', '০১৮৯৮৭৬৫৪৩২');
