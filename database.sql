CREATE DATABASE IF NOT EXISTS college_event_management;
USE college_event_management;

CREATE TABLE IF NOT EXISTS college_students (
 college_id VARCHAR(30) PRIMARY KEY, name VARCHAR(100) NOT NULL,
 department VARCHAR(100) NOT NULL, semester INT NOT NULL);

CREATE TABLE IF NOT EXISTS students (
 college_id VARCHAR(30) PRIMARY KEY, name VARCHAR(100) NOT NULL,
 email VARCHAR(120) UNIQUE NOT NULL, phone VARCHAR(20),
 department VARCHAR(100) NOT NULL, semester INT NOT NULL,
 password VARCHAR(100) NOT NULL,
 FOREIGN KEY (college_id) REFERENCES college_students(college_id));

CREATE TABLE IF NOT EXISTS organizers (
 organizer_id VARCHAR(30) PRIMARY KEY, name VARCHAR(100) NOT NULL,
 password VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS events (
 event_id VARCHAR(30) PRIMARY KEY, event_name VARCHAR(150) NOT NULL,
 event_date DATE NOT NULL, event_time TIME NOT NULL,
 venue VARCHAR(150) NOT NULL, capacity INT NOT NULL,
 description VARCHAR(500), organizer_id VARCHAR(30),
 FOREIGN KEY (organizer_id) REFERENCES organizers(organizer_id));

CREATE TABLE IF NOT EXISTS registrations (
 registration_id INT AUTO_INCREMENT PRIMARY KEY, college_id VARCHAR(30) NOT NULL,
 event_id VARCHAR(30) NOT NULL, registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 status VARCHAR(30) DEFAULT 'REGISTERED',
 UNIQUE (college_id,event_id),
 FOREIGN KEY (college_id) REFERENCES students(college_id),
 FOREIGN KEY (event_id) REFERENCES events(event_id));

INSERT IGNORE INTO college_students VALUES
('BJI001','Sample Student','Computer Science',3),
('BJI002','Anu Thomas','Computer Science',3),
('BJI003','Arjun Kumar','Data Science',3);

INSERT IGNORE INTO organizers VALUES ('ORG001','College Event Coordinator','admin123');

INSERT IGNORE INTO events
(event_id,event_name,event_date,event_time,venue,capacity,description,organizer_id)
VALUES ('E001','AI Workshop','2026-11-15','10:00:00','Seminar Hall',100,
'Introduction to Artificial Intelligence','ORG001');
