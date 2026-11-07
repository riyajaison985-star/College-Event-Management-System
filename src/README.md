# College Event Management System

A Java 17 console application for managing college event registration. The project uses object-oriented design, JDBC, MySQL and Maven.

## Features implemented

- Student account registration after checking the College ID against the `college_students` table
- Student and organizer login
- Event listing and organizer event creation
- Event registration with capacity checks
- Duplicate-registration prevention through a database unique constraint
- Viewing and cancelling a student's registrations
- Organizer participant listing
- JDBC database access separated into DAO classes

## Technology stack

- Java 17
- MySQL 8.x
- JDBC / MySQL Connector/J 9.4.0
- Maven
- IntelliJ IDEA (recommended IDE)

## Project structure

```text
College-Event-Management-System/
├── database.sql
├── pom.xml
├── README.md
├── .gitignore
└── src/main/java/collegeevent/
    ├── Main.java
    ├── Person.java
    ├── Student.java
    ├── Organizer.java
    ├── Event.java
    ├── Registration.java
    ├── DatabaseConnection.java
    ├── StudentDAO.java
    ├── EventDAO.java
    ├── OrganizerDAO.java
    └── RegistrationDAO.java
```

## Setup and run

1. Install JDK 17, Maven and MySQL Server.
2. Create the database and tables by running `database.sql` in MySQL Workbench or the MySQL command-line client.
3. Set the database connection environment variables before starting the app:

   **Windows PowerShell (current terminal):**
   ```powershell
   $env:CEMS_DB_URL="jdbc:mysql://localhost:3306/college_event_management"
   $env:CEMS_DB_USER="your_mysql_username"
   $env:CEMS_DB_PASSWORD="your_mysql_password"
   ```

   **macOS/Linux (current terminal):**
   ```bash
   export CEMS_DB_URL="jdbc:mysql://localhost:3306/college_event_management"
   export CEMS_DB_USER="your_mysql_username"
   export CEMS_DB_PASSWORD="your_mysql_password"
   ```

4. From the project root, build and run:
   ```bash
   mvn clean package
   mvn exec:java -Dexec.mainClass=collegeevent.Main
   ```
   Alternatively, open the project root (the folder containing `pom.xml`) in IntelliJ IDEA, reload Maven, and run `Main.java`.

## Demo data

The SQL script inserts sample College IDs (`BJI001`–`BJI003`), one sample organizer (`ORG001`) and an example event. The sample organizer password in the SQL seed is a demonstration credential only; change it before using any non-demo database. Do not reuse personal or institutional passwords.

## OOP and design concepts

- **Encapsulation:** entity data is held in private fields and exposed through methods.
- **Inheritance:** `Student` and `Organizer` extend the abstract `Person` class.
- **Abstraction:** `Person` declares the abstract `displayRole()` method.
- **Polymorphism:** `Student` and `Organizer` override `displayRole()`.
- **DAO pattern:** `StudentDAO`, `EventDAO`, `OrganizerDAO` and `RegistrationDAO` keep SQL operations separate from the console flow.

## Limitations and security notes

This is an academic console application, not a production-ready service. Passwords are currently stored and compared as plain text in the demo implementation. Use only dummy data; do not use real student information or real passwords. Password hashing, stronger authentication, concurrency-safe seat allocation, input validation and a graphical/web interface are possible future improvements.

## License

No license has been added. Add a license only if the project authors agree on the terms for reuse.
