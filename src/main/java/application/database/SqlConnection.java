package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/gestion-college-javafx";
    private static final String USER = "root"; // Consider moving to config
    private static final String PASSWORD = ""; // Consider moving to config

    static {
        try {
            // No need to manually load the driver class
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("SQL error during connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Failed to create connection: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null; // Reset the connection to null after closing
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
