
package bankingmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BankingManagementSystem {

    private static AccountManager accountManager = new AccountManager();
    private static TransactionManager transactionManager = new TransactionManager();
    private static LoanManager loanManager = new LoanManager();
    private static JavaDb database;

    public static void main(String[] args) {
        // Initialize the database and ensure the table exists
        JavaDb database = new JavaDb();

        // Build GUI
        JFrame frame = new JFrame("Banking Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        JButton createAccountBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transactionBtn = new JButton("View Transactions");
        JButton loanBtn = new JButton("Apply for Loan");
        JButton exitBtn = new JButton("Exit");

        createAccountBtn.addActionListener(e -> accountManager.createAccount());
        depositBtn.addActionListener(e -> accountManager.deposit());
        withdrawBtn.addActionListener(e -> accountManager.withdraw());
        transactionBtn.addActionListener(e -> transactionManager.displayTransactions());
        loanBtn.addActionListener(e -> loanManager.applyForLoan());
        exitBtn.addActionListener(e -> {
            database.closeConnection(); // Close DB on exit
            System.exit(0);
        });

        frame.add(createAccountBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(transactionBtn);
        frame.add(loanBtn);
        frame.add(exitBtn);

        frame.setVisible(true);
    }
}