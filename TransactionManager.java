
package bankingmanagementsystem;


import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class TransactionManager {
    private List<String> transactions = new ArrayList<>();
    private JavaDb database;

    public void setDatabase(JavaDb database) {
        this.database = database;
    }

    public void logTransaction(String accNum, String type, double amount) {
        String record = "Account: " + accNum + ", Type: " + type + ", Amount: " + amount;
        transactions.add(record);

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO Transactions (account_number, type, amount) VALUES (?, ?, ?)")) {
            stmt.setString(1, accNum);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to log transaction!");
        }
    }

    public void displayTransactions() {
        StringBuilder sb = new StringBuilder("=== TRANSACTIONS ===\n");
        
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions")) {
            
            while (rs.next()) {
                sb.append(rs.getString("account_number")).append(": ")
                  .append(rs.getString("type")).append(" $")
                  .append(rs.getDouble("amount")).append(" on ")
                  .append(rs.getTimestamp("date")).append("\n");
            }
            
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error reading transactions");
        }
    }
}
