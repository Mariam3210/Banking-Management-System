
package bankingmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class TransactionManager {
    private List<String> transactions = new ArrayList<>();

    public void logTransaction(String accNum, String type, double amount) {
        String record = "Account: " + accNum + ", Type: " + type + ", Amount: " + amount + ", Date: " + new Date();
        transactions.add(record);
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No transactions available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String t : transactions) {
                sb.append(t).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}