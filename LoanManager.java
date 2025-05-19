/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingmanagementsystem;


import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class LoanManager {
    private final Map<String, Double> loans = new HashMap<>();
    private JavaDb database;

    public void setDatabase(JavaDb database) {
        this.database = database;
    }

    public void applyForLoan() {
        String accNum = JOptionPane.showInputDialog("Enter Account Number:");
        double loanAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Loan Amount:"));
        double interestRate = Double.parseDouble(JOptionPane.showInputDialog("Enter Interest Rate (%):"));
        int months = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Installments:"));

        double installment = calculateInstallment(loanAmount, interestRate, months);
        loans.put(accNum, loanAmount);

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO Loans (account_number, amount, interest_rate, installments) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, accNum);
            stmt.setDouble(2, loanAmount);
            stmt.setDouble(3, interestRate);
            stmt.setInt(4, months);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to save loan!");
        }

        JOptionPane.showMessageDialog(null, "Loan Approved. Monthly Installment: " + installment);
    }

    public double calculateInstallment(double principal, double rate, int months) {
        rate = rate / 100 / 12;
        return (principal * rate * Math.pow(1 + rate, months)) / (Math.pow(1 + rate, months) - 1);
    }
}
