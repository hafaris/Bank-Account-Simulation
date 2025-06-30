import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankApp {
    private static Map<Integer, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextAccountNumber = 1001;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Access Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> accessAccount();
                case 3 -> {
                    System.out.println("Thank you for using the Bank App!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createAccount() {
        scanner.nextLine();  // consume newline
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Set a 4-digit PIN: ");
        String pin = scanner.nextLine();

        BankAccount account = new BankAccount(name, nextAccountNumber, pin);
        accounts.put(nextAccountNumber, account);
        System.out.println("Account created. Your Account Number is: " + nextAccountNumber);
        nextAccountNumber++;
    }

    private static void accessAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter 4-digit PIN: ");
        String pin = scanner.nextLine();

        BankAccount account = accounts.get(accNo);
        if (account != null && account.validatePin(pin)) {
            System.out.println("Welcome " + account.getAccountHolder() + "!");
            userMenu(account);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void userMenu(BankAccount account) {
        boolean session = true;

        while (session) {
            System.out.println("\n== ACCOUNT MENU ==");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amt = scanner.nextDouble();
                    account.deposit(amt);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = scanner.nextDouble();
                    account.withdraw(amt);
                }
                case 3 -> account.checkBalance();
                case 4 -> account.viewTransactions();
                case 5 -> session = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
