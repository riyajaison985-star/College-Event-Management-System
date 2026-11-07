package collegeevent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = System.getenv().getOrDefault(
            "CEMS_DB_URL", "jdbc:mysql://localhost:3306/college_event_management");
    private static final String USER = System.getenv("CEMS_DB_USER");
    private static final String PASSWORD = System.getenv("CEMS_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (USER == null || PASSWORD == null) {
            throw new SQLException("Set CEMS_DB_USER and CEMS_DB_PASSWORD environment variables before running the application.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
