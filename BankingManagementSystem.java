
package bankingmanagementsystem;


import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BankingManagementSystem {
    private static AccountManager accountManager = new AccountManager();
    private static TransactionManager transactionManager = new TransactionManager();
    private static LoanManager loanManager = new LoanManager();
    private static JavaDb database;

    public static void main(String[] args) {
        
        database = new JavaDb();
      
        accountManager.setDatabase(database);
        transactionManager.setDatabase(database);
        loanManager.setDatabase(database);

       
        JFrame frame = new JFrame("MSA Bank");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

       
        JButton createAccountBtn = new JButton("Create an Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transactionBtn = new JButton("View Transactions");
        JButton loanBtn = new JButton("Apply for a Loan");
        JButton exitBtn = new JButton("Exit");

       
        createAccountBtn.addActionListener(e -> accountManager.createAccount());
        depositBtn.addActionListener(e -> accountManager.deposit());
        withdrawBtn.addActionListener(e -> accountManager.withdraw());
        transactionBtn.addActionListener(e -> transactionManager.displayTransactions());
        loanBtn.addActionListener(e -> loanManager.applyForLoan());
        exitBtn.addActionListener(e -> {
            database.closeConnection();
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
