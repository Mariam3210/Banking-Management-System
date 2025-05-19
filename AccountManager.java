package bankingmanagementsystem;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class AccountManager {
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount() {
        String accNum = JOptionPane.showInputDialog("Enter Account Number:");
        String name = JOptionPane.showInputDialog("Enter Customer Name:");
        String mobile = JOptionPane.showInputDialog("Enter Mobile Number:");
        double deposit = Double.parseDouble(JOptionPane.showInputDialog("Initial Deposit Amount:"));

        Account account = new Account(accNum, name, mobile, deposit);
        accounts.put(accNum, account);
        JOptionPane.showMessageDialog(null, "Account created for " + name);
    }

    public void deposit() {
        String accNum = JOptionPane.showInputDialog("Enter Account Number:");
        if (accounts.containsKey(accNum)) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Deposit Amount:"));
            Account acc = accounts.get(accNum);
            acc.setBalance(acc.getBalance() + amount);
            JOptionPane.showMessageDialog(null, "Deposit successful. New Balance: " + acc.getBalance());
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
        }
    }

    public void withdraw() {
        String accNum = JOptionPane.showInputDialog("Enter Account Number:");
        if (accounts.containsKey(accNum)) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Withdraw Amount:"));
            Account acc = accounts.get(accNum);
            if (amount <= acc.getBalance()) {
                acc.setBalance(acc.getBalance() - amount);
                JOptionPane.showMessageDialog(null, "Withdrawal successful. New Balance: " + acc.getBalance());
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient funds.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
        }
    }

    public double getBalance(String accNum) {
        Account acc = accounts.get(accNum);
        return acc != null ? acc.getBalance() : 0.0;
    }
}

class Account {
    private String accountNumber;
    private String customerName;
    private String mobileNumber;
    private double balance;

    public Account(String accountNumber, String customerName, String mobileNumber, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
