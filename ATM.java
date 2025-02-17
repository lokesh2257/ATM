import java.util.Scanner;

class Account {
    private double balance;
    private int pin;

    public Account(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public boolean authenticate(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}

class Main{
    private Account account;
    private Scanner scanner;

    public Main(Account account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        
        if (!account.authenticate(enteredPin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\u001B[32BrainWave ATM\u001B[0m");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using BrainWave ATM. Visit Again");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Account userAccount = new Account(10000.0, 7890); // Initial balance: $1000, PIN: 1234
        Main atm = new Main(userAccount);
        atm.start();
    }
}
