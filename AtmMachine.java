import java.util.Scanner;

public class AtmMachine {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Starting balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Money deposited: %.2f%n", amount);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Money withdrawn: %.2f%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n------- Welcome to ATM -------");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Exit");
    }

    public void start() {
        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next(); // consume invalid input
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void checkBalance() {
        System.out.printf("Current balance: %.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = readDoubleInput();
        account.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = readDoubleInput();
        account.withdraw(amount);
    }

    private double readDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next(); // consume invalid input
        }
        return scanner.nextDouble();
    }
}
