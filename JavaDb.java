
package bankingmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JavaDb {
    private Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "banking_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // REPLACE THIS

    public JavaDb() {
        try {
            // Use correct driver for MySQL 8+
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            showError("MySQL JDBC Driver not found!\n"
                    + "Make sure mysql-connector-java-5.1.46.jar is in your classpath.");
            return;
        }

        try {
            // Connect to MySQL without specifying DB first
            conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASSWORD);

            try (Statement stmt = conn.createStatement()) {
                // Create DB if not exists
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                // Switch to that DB
                conn.setCatalog(DB_NAME);
                // Create table if not exists
                String tableSQL = "CREATE TABLE IF NOT EXISTS Bank (" +
                        "account_number INT PRIMARY KEY," +
                        "customer_name VARCHAR(100) NOT NULL," +
                        "mobile_number VARCHAR(15) NOT NULL," +
                        "balance DECIMAL(15,2) NOT NULL DEFAULT 0.00)";
                stmt.executeUpdate(tableSQL);
                System.out.println("Database and table 'Bank' initialized.");
            }

        } catch (SQLException e) {
            showError("Failed to connect or create table.\n" + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, 
                message, 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            showError("Error closing connection: " + e.getMessage());
        }
    }
}