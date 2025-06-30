import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder, int accountNumber, String pin) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account created with balance ₹0.00");
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }
        balance += amount;
        logTransaction("Deposited ₹" + amount);
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        logTransaction("Withdrew ₹" + amount);
        System.out.println("Withdrawal successful.");
    }

    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void viewTransactions() {
        System.out.println("\n--- Transaction History ---");
        for (String entry : transactionHistory) {
            System.out.println(entry);
        }
    }

    private void logTransaction(String entry) {
        transactionHistory.add(entry + " | Balance: ₹" + balance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
