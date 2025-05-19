
package bankingmanagementsystem;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class LoanManager {
    
    private final Map<String, Double> loans = new HashMap<>();

    public void applyForLoan() {
        String accNum = JOptionPane.showInputDialog("Enter Account Number:");
        double loanAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Loan Amount:"));
        double interestRate = Double.parseDouble(JOptionPane.showInputDialog("Enter Interest Rate (%):"));
        int months = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Installments:"));

        double installment = calculateInstallment(loanAmount, interestRate, months);
        loans.put(accNum, loanAmount);
        JOptionPane.showMessageDialog(null, "Loan Approved. Monthly Installment: " + installment);
    }

    public double calculateInstallment(double principal, double rate, int months) {
        rate = rate / 100 / 12;
        return (principal * rate * Math.pow(1 + rate, months)) / (Math.pow(1 + rate, months) - 1);
    }
}