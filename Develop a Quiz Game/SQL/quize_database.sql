-- Create Database
CREATE DATABASE IF NOT EXISTS quiz_game;
USE quiz_game;

-- Questions Table (Store 20 questions)
CREATE TABLE IF NOT EXISTS questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255),
    option_b VARCHAR(255),
    option_c VARCHAR(255),
    option_d VARCHAR(255),
    correct_answer CHAR(1) CHECK (correct_answer IN ('A', 'B', 'C', 'D')),
    category VARCHAR(100)
);

-- Insert 20 Sample Questions
INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer, category) VALUES
('What is the capital of France?', 'London', 'Berlin', 'Paris', 'Madrid', 'C', 'Geography'),
('Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'B', 'Science'),
('What is 2 + 2?', '3', '4', '5', '6', 'B', 'Math'),
('Who wrote "Hamlet"?', 'Charles Dickens', 'William Shakespeare', 'Mark Twain', 'Jane Austen', 'B', 'Literature'),
('Which language is used for Android development?', 'Python', 'Java', 'C++', 'JavaScript', 'B', 'Programming'),
('What is the largest ocean?', 'Atlantic', 'Indian', 'Arctic', 'Pacific', 'D', 'Geography'),
('What is the chemical symbol for Gold?', 'Go', 'Gd', 'Au', 'Ag', 'C', 'Science'),
('What is 5 × 6?', '25', '30', '35', '40', 'B', 'Math'),
('Who painted the Mona Lisa?', 'Vincent van Gogh', 'Pablo Picasso', 'Leonardo da Vinci', 'Michelangelo', 'C', 'Art'),
('What does CPU stand for?', 'Central Processing Unit', 'Computer Personal Unit', 'Central Process Unit', 'Computer Processing Unit', 'A', 'Technology'),
('Which is the smallest country?', 'Monaco', 'Vatican City', 'San Marino', 'Liechtenstein', 'B', 'Geography'),
('What is H₂O?', 'Water', 'Oxygen', 'Hydrogen', 'Salt', 'A', 'Science'),
('What is 15 ÷ 3?', '3', '4', '5', '6', 'C', 'Math'),
('Who discovered gravity?', 'Albert Einstein', 'Isaac Newton', 'Galileo', 'Nikola Tesla', 'B', 'Science'),
('Which is not a programming language?', 'Java', 'Python', 'HTML', 'C++', 'C', 'Programming'),
('What is the fastest land animal?', 'Lion', 'Cheetah', 'Leopard', 'Tiger', 'B', 'Animals'),
('How many continents are there?', '5', '6', '7', '8', 'C', 'Geography'),
('What is the square root of 64?', '6', '7', '8', '9', 'C', 'Math'),
('Which company developed Java?', 'Microsoft', 'Apple', 'Sun Microsystems', 'Google', 'C', 'Programming'),
('What is the hardest natural substance?', 'Gold', 'Iron', 'Diamond', 'Platinum', 'C', 'Science');

-- Scores Table (Save player name & score)
CREATE TABLE IF NOT EXISTS scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(100) NOT NULL,
    score INT DEFAULT 0,
    date_played TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
