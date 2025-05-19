
package bankingmanagementsystem;


import java.sql.*;
import javax.swing.JOptionPane;

public class JavaDb {
    private Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "banking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public JavaDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASSWORD);
            
            try (Statement stmt = conn.createStatement()) {
                // Create tables if they don't exist
                stmt.execute("CREATE TABLE IF NOT EXISTS Bank (" +
                    "account_number VARCHAR(50) PRIMARY KEY," +
                    "customer_name VARCHAR(100) NOT NULL," +
                    "mobile_number VARCHAR(15) NOT NULL," +
                    "balance DECIMAL(15,2) NOT NULL)");
                
                stmt.execute("CREATE TABLE IF NOT EXISTS Transactions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "account_number VARCHAR(50)," +
                    "type VARCHAR(20)," +
                    "amount DECIMAL(15,2)," +
                    "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
                
                stmt.execute("CREATE TABLE IF NOT EXISTS Loans (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "account_number VARCHAR(50)," +
                    "amount DECIMAL(15,2)," +
                    "interest_rate DECIMAL(5,2)," +
                    "installments INT)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Database Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
